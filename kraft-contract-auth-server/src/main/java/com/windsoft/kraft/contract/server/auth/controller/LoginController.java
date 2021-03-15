package com.windsoft.kraft.contract.server.auth.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.windsoft.kraft.contract.common.dto.UserDto;
import com.windsoft.kraft.contract.common.utils.CommonUtils;
import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.server.auth.fegin.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("auth")
public class LoginController {

    @Autowired
    private UserServer userServer;

    @PostMapping("login")
    public JsonResult login(@RequestBody UserDto userDto){
        JsonResult result = userServer.login(userDto);
        if (result.getCode()  == 0){
            Map<String,Object> map = JSONObject.parseObject(JSON.toJSONString(result.getData()));
            UserDto user = JSON.parseObject(JSON.toJSONString(map), UserDto.class);
            if(user.getPassword().equals(CommonUtils.password(userDto.getPassword()))){
                // 返回结果
                Map<String, String> token = new HashMap<>();
                token.put("access_token", "bbd87dcb-ab3b-4974-8186-d16af4958384");
                token.put("token_type", "Bearer");
                return JsonResult.success(token);
            }else{
                return JsonResult.error("密码错误");
            }
        }
        return JsonResult.error("账号错误");
    }
}
