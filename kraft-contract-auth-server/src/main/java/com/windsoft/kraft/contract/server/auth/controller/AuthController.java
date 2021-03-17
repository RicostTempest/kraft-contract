package com.windsoft.kraft.contract.server.auth.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.windsoft.kraft.contract.common.dto.UserDto;
import com.windsoft.kraft.contract.common.utils.CommonUtils;
import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.common.utils.RedisUtils;
import com.windsoft.kraft.contract.server.auth.fegin.EmailServer;
import com.windsoft.kraft.contract.server.auth.fegin.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private UserServer userServer;
    @Autowired
    private EmailServer emailServer;
    @Autowired
    private RedisUtils redisUtil;

    @PostMapping("login")
    public JsonResult login(@RequestBody UserDto userDto){
        JsonResult result = userServer.login(userDto);
        if (result.getCode()  == 0){
            Map<String,Object> map = JSONObject.parseObject(JSON.toJSONString(result.getData()));
            UserDto user = JSON.parseObject(JSON.toJSONString(map), UserDto.class);
            if(user.getPassword().equals(CommonUtils.password(userDto.getPassword()))){
                // 返回结果
                Map<String, Object> token = new HashMap<>();
                token.put("access_token", "bbd87dcb-ab3b-4974-8186-d16af4958384");
                token.put("token_type", "Bearer");
                token.put("user", user);
                return JsonResult.success(token);
            }else{
                return JsonResult.error("密码错误");
            }
        }
        return JsonResult.error("账号错误");
    }

    @GetMapping("logout")
    public JsonResult logout(){
        return JsonResult.success("注销成功");
    }

    @GetMapping("forget/code/{email}")
    public JsonResult forgetCode(@PathVariable("email") String email){
        return emailServer.createCaptcha(email);
    }

    @PostMapping("forget")
    public JsonResult forget(@RequestBody UserDto userDto){
        JsonResult result = userServer.userExist(JSON.toJSONString(userDto));
        if (result.getCode() == 0){
            String code = (String) redisUtil.get("email:code:"+CommonUtils.md5(userDto.getEmail()));
            if (userDto.getCode().equals(code)){
                Map<String,Object> map = JSONObject.parseObject(JSON.toJSONString(result.getData()));
                map.put("password",CommonUtils.password(userDto.getPassword()));
                result = userServer.resetPassword(JSON.toJSONString(map));
                if (result.getCode()==0){
                    return JsonResult.success();
                }
            }
        }
        return result;
    }
}
