package com.windsoft.kraft.contract.consumer.pu.fegin;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.consumer.pu.fegin.fallback.FileServerFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Primary
@FeignClient(value = "kraft-contract-file-server", fallback = FileServerFallback.class)
public interface FileServer {
    @PostMapping("file/resource/{projectId}")
    public JsonResult resourceSave(@PathVariable("projectId") Long id,@RequestParam("files") String files);
}
