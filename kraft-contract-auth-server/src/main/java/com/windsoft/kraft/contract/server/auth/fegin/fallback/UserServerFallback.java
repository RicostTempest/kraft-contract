package com.windsoft.kraft.contract.server.auth.fegin.fallback;

import com.windsoft.kraft.contract.common.dto.UserDto;
import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.server.auth.fegin.UserServer;
import org.springframework.stereotype.Component;

@Component
public class UserServerFallback implements UserServer {

    @Override
    public JsonResult login(UserDto userDto) {
        return JsonResult.error(400, "用户服务正在维护");
    }
}
