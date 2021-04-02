package com.windsoft.kraft.contract.server.flow.fegin.fallback;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.server.flow.fegin.UserServer;
import org.springframework.stereotype.Component;

@Component
public class UserServerFallback implements UserServer {
    @Override
    public JsonResult accountDetail(Long id) {
        return JsonResult.error(400, "用户服务发生错误");
    }
}
