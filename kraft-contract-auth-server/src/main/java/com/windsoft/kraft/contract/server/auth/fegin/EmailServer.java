package com.windsoft.kraft.contract.server.auth.fegin;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.server.auth.fegin.fallback.EmailServerFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Primary
@FeignClient(value = "kraft-contract-email-server", fallback = EmailServerFallback.class)
public interface EmailServer {
    @GetMapping("/email/captcha/{email}")
    public JsonResult createCaptcha(@PathVariable String email);
}
