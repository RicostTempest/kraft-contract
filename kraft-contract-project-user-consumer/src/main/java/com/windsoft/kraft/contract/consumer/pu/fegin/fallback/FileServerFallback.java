package com.windsoft.kraft.contract.consumer.pu.fegin.fallback;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.consumer.pu.fegin.FileServer;
import org.springframework.stereotype.Component;

@Component
public class FileServerFallback implements FileServer {
    @Override
    public JsonResult resourceSave(Long id, String files) {
        return JsonResult.error(400,"文件服务维护中");
    }
}
