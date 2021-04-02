package com.windsoft.kraft.contract.server.file.service.impl;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.mybatis.domain.Invoice;
import com.windsoft.kraft.contract.server.file.service.FileService;
import com.windsoft.kraft.contract.server.file.utils.DFSFileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.EOFException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {

    @Value("${upload.host}")
    private String host;
    @Value("${upload.res.avatar}")
    String avatar;
    @Value("${upload.res.doc}")
    String doc;

    @Override
    public JsonResult imgUpload(MultipartFile file) {
        String msg = DFSFileUtils.uploadFile(file, host, avatar);
        if ("error".equals(msg)){
            return JsonResult.error();
        }
        return JsonResult.success(msg);
    }

    @Override
    public JsonResult fileDelete(String path) {
        if (DFSFileUtils.deleteFile(host,path)){
            return JsonResult.success();
        }
        return JsonResult.error();
    }

    @Override
    public JsonResult docUpload(MultipartFile file){
        String msg = DFSFileUtils.uploadFile(file, host, doc);
        if ("error".equals(msg)){
            return JsonResult.error();
        }
        return JsonResult.success(msg);
    }

    @Override
    public JsonResult getPDFInfo(MultipartFile file) {
        PDDocument document = null;
        String msg = null;
        Invoice invoice = null;
        try {
            InputStream stream = file.getInputStream();
            document = PDDocument.load(stream);
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            PDPageTree pages = document.getPages();
            int pageCount = pages.getCount();
            for (int i = 0; i < pageCount; i++) {
                BufferedImage bim = pdfRenderer.renderImageWithDPI(i, 200);
                LuminanceSource source = new BufferedImageLuminanceSource(bim);
                Binarizer binarizer = new HybridBinarizer(source);
                BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
                Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
                hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
                //解码
                Result result = new MultiFormatReader().decode(binaryBitmap, hints);
                String[] invoiceCode = result.getText().split(",");

                invoice = new Invoice();
                invoice.setInvoiceCode(invoiceCode[2]);
                invoice.setInvoiceNumber(invoiceCode[3]);
                invoice.setValue(new BigDecimal(invoiceCode[4]));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                Date date = simpleDateFormat.parse(invoiceCode[5]);
                invoice.setInvoiceDate(date);
                invoice.setName(invoiceCode[7]);
                msg = DFSFileUtils.uploadFile(file, host, doc);
            }
        } catch (EOFException eofException){
            eofException.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        if ("error".equals(msg)){
            return JsonResult.error();
        }
        Map<String,Object> map = new HashMap<>();
        map.put("path", msg);
        map.put("invoice", invoice);
        return JsonResult.success(map);
    }
}
