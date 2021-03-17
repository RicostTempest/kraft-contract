package com.windsoft.user;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.windsoft.kraft.contract.common.dto.UserDto;
import com.windsoft.kraft.contract.common.utils.CommonUtils;
import com.windsoft.kraft.contract.mybatis.domain.User;
import com.windsoft.kraft.contract.server.user.UserServerApplication;
import com.windsoft.kraft.contract.server.user.dto.UserListDto;
import com.windsoft.kraft.contract.server.user.entity.AuthEntity;
import com.windsoft.kraft.contract.server.user.mapper.UserMapper;
import com.windsoft.kraft.contract.server.user.query.UserInfoQuery;
import com.windsoft.kraft.contract.server.user.service.AuthService;
import com.windsoft.kraft.contract.server.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserServerApplication.class)
public class UserTest {
    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;


    @Test
    public void testSelectAll(){
        List<User> users = userMapper.selectAll();
        users.forEach(umsAdmin -> {
            System.out.println(umsAdmin);
        });

    }

    @Test
    public void addPassword(){
        User user = userMapper.selectByPrimaryKey(1);
        user.setPassword(CommonUtils.password("1722110122"));
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Test
    public void serviceTest(){
        Integer[] ids = {1,2,3,4,5};
        System.out.println(CommonUtils.integerArrayToString(ids, ","));
    }

    @Test
    public void pageTest(){
        PageHelper.startPage(1,3);
        List<User> users = userMapper.selectAll();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        System.out.println(pageInfo.getList().size());
    }

    @Test
    public void uuid(){
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
    }

    @Test
    public void password(){
        System.out.println(CommonUtils.password("111111"));
    }

    @Test
    public void authAccount(){
        AuthEntity authEntity = new AuthEntity();
        authEntity.setIsStudent((byte) 1);
        authEntity.setNumber("1722110122");
        authEntity.setPassword("1722110122xs@");
        authService.authUser(authEntity, 1L);
    }

    @Test
    public void userSearchTest(){
        UserInfoQuery dto = new UserInfoQuery();
//        dto.setId(1L);
//        dto.setName("陈");
        dto.setNumber("15");

        List<UserListDto> dtos = userMapper.selectUserAuth(dto);
        dtos.forEach(item-> System.out.println(item));
    }

    @Test
    public void jsonTest(){
        User user = new User();
        user.setPassword("123456");
        user.setEmail("RCOST@FOXMAIL.COM");
        String json = JSON.toJSONString(user);
        UserDto userDto = JSON.parseObject(json, UserDto.class);
        System.out.println(userDto);
    }


    @Test
    public void updatePassword(){
        User user = new User();
        user.setPassword("1234");
        user.setId(2l);
        System.out.println(userMapper.updatePassword(user));
    }


}
