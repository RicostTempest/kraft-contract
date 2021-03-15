package com.windsoft.kraft.contract.fegin;

import com.windsoft.kraft.contract.common.dto.UserDto;
import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.fegin.fallback.UserServerFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Primary
@FeignClient(value = "kraft-contract-user-server", fallback = UserServerFallback.class)
public interface UserServer {
    @GetMapping("/user/info/{userId}")
    public JsonResult infoUser(@PathVariable("userId") Long id);

    @PostMapping("/user/login")
    public JsonResult login(@RequestBody UserDto userDto);
}
