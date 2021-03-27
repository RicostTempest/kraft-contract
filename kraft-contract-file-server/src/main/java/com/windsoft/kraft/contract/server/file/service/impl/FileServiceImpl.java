package com.windsoft.kraft.contract.server.file.service.impl;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.server.file.service.FileService;
import com.windsoft.kraft.contract.server.file.utils.DFSFileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
}
