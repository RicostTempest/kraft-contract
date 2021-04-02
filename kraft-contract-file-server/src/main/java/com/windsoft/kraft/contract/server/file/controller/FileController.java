package com.windsoft.kraft.contract.server.file.controller;

import com.alibaba.fastjson.JSON;
import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.mybatis.domain.Resource;
import com.windsoft.kraft.contract.server.file.service.FileService;
import com.windsoft.kraft.contract.server.file.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private ResourceService resourceService;

    @PostMapping("upload/img")
    public JsonResult uploadImage(@RequestParam("file") MultipartFile file) {
        return fileService.imgUpload(file);
    }

    @PostMapping("upload/doc")
    public JsonResult uploadDoc(@RequestParam("file") MultipartFile file) {
        return fileService.docUpload(file);
    }

    @DeleteMapping("delete/img")
    public JsonResult deleteImage(@RequestParam("path") String path){
        return fileService.fileDelete(path);
    }

    @PostMapping("resource/{projectId}")
    public JsonResult resourceSave(@PathVariable("projectId") Long id,@RequestParam("files")String files){
        List<Resource> resources = JSON.parseArray(files, Resource.class);
        return resourceService.addResources(id, resources);
    }

    @GetMapping("resources/{projectId}")
    public JsonResult getResources(@PathVariable("projectId") Long id){
        return resourceService.getResources(id);
    }

    @GetMapping("resource/{resourceId}")
    public JsonResult getResource(@PathVariable("resourceId") Long id){
        return resourceService.getResourcesById(id);
    }

    @PostMapping("upload/pdf")
    public JsonResult pdfInfoGet(@RequestParam("file") MultipartFile file){
        return fileService.getPDFInfo(file);
    }

}
