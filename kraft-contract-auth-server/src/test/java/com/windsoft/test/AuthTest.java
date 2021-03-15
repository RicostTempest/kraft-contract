package com.windsoft.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.windsoft.kraft.contract.common.dto.UserDto;
import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.server.auth.AuthApplication;
import com.windsoft.kraft.contract.server.auth.fegin.UserServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthApplication.class)
public class AuthTest {
    @Autowired
    UserServer userServer;

    @Test
    public void authTest(){
        UserDto userDto = new UserDto();
        userDto.setUsername("Ricost");
        JsonResult result = userServer.login(userDto);
        if (result.getCode()  == 0){
            Map<String,Object> map = JSONObject.parseObject(JSON.toJSONString(result.getData()));
            UserDto user = JSON.parseObject(JSON.toJSONString(map), UserDto.class);
            System.out.println(user.getEmail());
        }else{
            System.out.println("error");
        }
    }
}
