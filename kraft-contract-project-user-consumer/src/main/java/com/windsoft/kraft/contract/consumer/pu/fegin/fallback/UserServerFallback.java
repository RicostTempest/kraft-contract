package com.windsoft.kraft.contract.consumer.pu.fegin.fallback;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.consumer.pu.fegin.UserServer;
import org.springframework.stereotype.Component;

@Component
public class UserServerFallback implements UserServer {
    @Override
    public JsonResult infoUser(Long id) {
        return JsonResult.error(400, "用户服务正在维护");
    }
}
