package com.windsoft.test;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.spire.pdf.PdfDocument;
import com.windsoft.kraft.contract.mybatis.domain.Resource;
import com.windsoft.kraft.contract.server.file.FileServerApplication;
import com.windsoft.kraft.contract.server.file.mapper.ResourceMapper;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = FileServerApplication.class)
public class ResourceTest {
    @Autowired
    ResourceMapper resourceMapper;

    @Test
    public void resourceGet(){
        List<Resource> resources = resourceMapper.selectByProjectId(35L);
        System.out.println(resources);

    }

    @Test
    public void pdfTest(){
        PDDocument doc = null;
        InputStream stream = null;

        OutputStream out = null;
        String content = null;

        /*InputStream is = null;
        OutputStream responseOut = null;*/
        try{
            //pdf路径
            stream = new FileInputStream(new File("C:\\Users\\76069\\Desktop\\pdf.pdf"));
            // 加载解析PDF文件
            doc = PDDocument.load(stream);
            PDFRenderer pdfRenderer = new PDFRenderer(doc);
            PDPageTree pages = doc.getPages();
            int pageCount = pages.getCount();
            for (int i = 0; i < pageCount; i++) {
                BufferedImage bim = pdfRenderer.renderImageWithDPI(i, 200);
                LuminanceSource source = new BufferedImageLuminanceSource(bim);
                Binarizer binarizer = new HybridBinarizer(source);
                BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
                Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
                hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
                Result result = new MultiFormatReader().decode(binaryBitmap, hints);//解码
                System.out.println("图片中内容：  ");
                System.out.println("content： " + result.getText());

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (doc != null) {
                try {
                    doc.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (stream != null) {
                try {
                    stream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testCodeLoad(){
        String content = null;
        BufferedImage image;
        try {
            image = ImageIO.read(new File("C:\\Users\\76069\\Desktop\\abc0.jpg"));
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            Result result = new MultiFormatReader().decode(binaryBitmap, hints);//解码
            System.out.println("图片中内容：  ");
            System.out.println("content： " + result.getText());
            content = result.getText();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            //这里判断如果识别不了带LOGO的图片，重新添加上一个属性
            try {
                image = ImageIO.read(new File("C:\\Users\\76069\\Desktop\\abc0.jpg"));
                LuminanceSource source = new BufferedImageLuminanceSource(image);
                Binarizer binarizer = new HybridBinarizer(source);
                BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
                Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
                //设置编码格式
                hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
                //设置优化精度
                hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
                //设置复杂模式开启（我使用这种方式就可以识别微信的二维码了）
                hints.put(DecodeHintType.PURE_BARCODE,Boolean.TYPE);
                Result result = new MultiFormatReader().decode(binaryBitmap, hints);//解码
                System.out.println("图片中内容：  ");
                System.out.println("content： " + result.getText());
                content = result.getText();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (NotFoundException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Test
    public void testPDFToImg(){
        //加载PDF文件
        PdfDocument pdf = new PdfDocument();
        pdf.loadFromFile("C:\\Users\\76069\\Desktop\\pdf.pdf");

//保存PDF的每一页到图片
        BufferedImage image;

        for (int i = 0; i < pdf.getPages().getCount(); i++) {
            image = pdf.saveAsImage(i);
            File file = new File( String.format("C:\\Users\\76069\\Desktop\\ToImage-img-%d.png", i));
            try {
                ImageIO.write(image, "jpg", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        pdf.close();
    }

    @Test
    public void freePdfToString(){
        PdfDocument pdf = new PdfDocument();
        pdf.loadFromFile("C:\\Users\\76069\\Desktop\\pdf.pdf");
        BufferedImage image;
        String content;

        for (int i = 0; i < pdf.getPages().getCount(); i++) {
            image = pdf.saveAsImage(i);
            File file = new File( String.format("C:\\Users\\76069\\Desktop\\ToImage-img-%d.jpg", i));
            try {
                ImageIO.write(image, "jpg", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
            //设置编码格式
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            //设置优化精度
            hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
            //设置复杂模式开启（我使用这种方式就可以识别微信的二维码了）
            hints.put(DecodeHintType.PURE_BARCODE,Boolean.TYPE);
            Result result = null;//解码
            try {
                result = new MultiFormatReader().decode(binaryBitmap, hints);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("图片中内容：  ");
            System.out.println("content： " + result.getText());
            content = result.getText();
        }

        pdf.close();
    }

    @Test
    public void testDate() throws ParseException {
        String string = "20200429";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = simpleDateFormat.parse(string);
        System.out.println(date);
    }
}
