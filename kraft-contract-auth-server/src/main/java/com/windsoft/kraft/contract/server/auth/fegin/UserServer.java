package com.windsoft.kraft.contract.server.auth.fegin;

import com.windsoft.kraft.contract.common.dto.UserDto;
import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.server.auth.fegin.fallback.UserServerFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;

@Primary
@FeignClient(value = "kraft-contract-user-server", fallback = UserServerFallback.class)
public interface UserServer {
    @PostMapping("/user/login")
    public JsonResult login(@RequestBody UserDto userDto);

    @PutMapping("/user/reset/{userIds}")
    public JsonResult reset(@PathVariable("userIds") Long[] ids);

    @GetMapping("/user/exist")
    public JsonResult userExist(@RequestParam("user") String entity);

    @PostMapping("/user/reset/password")
    public JsonResult resetPassword(@RequestParam("user") String entity);
}
