package com.windsoft.kraft.contract.server.file.controller;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.server.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("upload/img")
    public JsonResult uploadImage(@RequestParam("file") MultipartFile file) {
        return fileService.fileUpload(file);
    }

    @DeleteMapping("delete/img")
    public JsonResult deleteImage(@RequestParam("path") String path){
        return fileService.fileDelete(path);
    }
}
