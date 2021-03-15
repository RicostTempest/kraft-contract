package com.windsoft.kraft.contract.common.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private Long id;
    /**
     * 电话
     */
    private String telephone;

    private String email;

    private String password;

    /**
     * 用户名：唯一不可重复不可修改
     */
    private String username;
}
