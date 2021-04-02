package com.windsoft.kraft.contract.server.flow.fegin;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.server.flow.fegin.fallback.UserServerFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Primary
@FeignClient(value = "kraft-contract-user-server", fallback = UserServerFallback.class)
public interface UserServer {
    @GetMapping("user/account/{userId}/detail")
    public JsonResult accountDetail(@PathVariable("userId") Long id);
}
