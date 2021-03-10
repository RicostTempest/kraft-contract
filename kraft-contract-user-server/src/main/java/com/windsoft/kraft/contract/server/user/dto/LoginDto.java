package com.windsoft.kraft.contract.server.user.dto;

import lombok.Data;

@Data
public class LoginDto {

    /**
     * 登录用户名
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;


    /**
     * 验证码KEY
     */
    private String key;

}
