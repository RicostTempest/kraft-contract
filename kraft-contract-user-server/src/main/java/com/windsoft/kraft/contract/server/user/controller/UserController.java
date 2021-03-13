package com.windsoft.kraft.contract.server.user.controller;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.mybatis.domain.User;
import com.windsoft.kraft.contract.server.user.entity.AuthEntity;
import com.windsoft.kraft.contract.server.user.query.UserQuery;
import com.windsoft.kraft.contract.server.user.service.AuthService;
import com.windsoft.kraft.contract.server.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;

    @GetMapping("find")
    public JsonResult find(UserQuery userQuery){
        return userService.getList(userQuery);
    }

    @PutMapping("edit")
    public JsonResult update(@RequestBody User entity) {
        return userService.update(entity);
    }

    @PostMapping("add")
    public JsonResult add(@RequestBody User entity){
        return userService.add(entity);
    }

    @DeleteMapping("delete/{userIds}")
    public JsonResult delete(@PathVariable("userIds") Long[] ids){
        return userService.deleteByIds(ids);
    }

    @GetMapping("info/{userId}")
    public JsonResult info(@PathVariable("userId") Long id){
        return userService.info(id);
    }

    @PutMapping("reset/{userIds}")
    public JsonResult reset(@PathVariable("userIds") Long[] ids){
        return userService.resetPasswordByIds(ids);
    }

    @GetMapping("auth/{userId}")
    public JsonResult auth(@PathVariable("userId") Long id){
        return authService.getAccount(id);
    }

    @DeleteMapping("auth/free/{authId}")
    public JsonResult freeBand(@PathVariable("authId") Long id){
        return authService.freeBand(id);
    }

    @PostMapping("auth/band/{authId}")
    public JsonResult add(@PathVariable("authId") Long id, @RequestBody AuthEntity entity){
        return authService.authUser(entity, id);
    }
}
