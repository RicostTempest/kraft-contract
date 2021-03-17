package com.windsoft.kraft.contract.server.auth.fegin.fallback;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.server.auth.fegin.EmailServer;
import org.springframework.stereotype.Component;

@Component
public class EmailServerFallback implements EmailServer {
    @Override
    public JsonResult createCaptcha(String email) {
        return JsonResult.error(400, "邮件服务未启动");
    }
}
