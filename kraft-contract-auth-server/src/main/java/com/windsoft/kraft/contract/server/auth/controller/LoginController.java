package com.windsoft.kraft.contract.server.auth.controller;

import com.windsoft.kraft.contract.common.dto.UserDto;
import com.windsoft.kraft.contract.common.utils.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class LoginController {

//    @Autowired
//    private UserServer userServer;

    @PostMapping("login")
    public JsonResult login(@RequestBody UserDto userDto){
//        JsonResult result = userServer.login(userDto);
//        if (result.getCode()  == 0){
//            Map<String,Object> map = JSONObject.parseObject(JSON.toJSONString(result.getData()));
//            UserDto user = JSON.parseObject(JSON.toJSONString(map), UserDto.class);
//        }

        return JsonResult.error("账号错误");
    }
}
