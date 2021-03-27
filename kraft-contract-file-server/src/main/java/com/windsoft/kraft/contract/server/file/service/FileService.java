package com.windsoft.kraft.contract.server.file.service;


import com.windsoft.kraft.contract.common.utils.JsonResult;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public JsonResult imgUpload(MultipartFile file);
    public JsonResult fileDelete(String path);

    JsonResult docUpload(MultipartFile file);
}
