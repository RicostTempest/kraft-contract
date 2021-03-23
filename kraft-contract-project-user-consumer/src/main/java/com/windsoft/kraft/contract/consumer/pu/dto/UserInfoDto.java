package com.windsoft.kraft.contract.consumer.pu.dto;

import lombok.Data;

@Data
public class UserInfoDto {
    /**
     * 用户id
     */
    private Long id;
    /**
     * 用户认证账号的真实姓名
     */
    private String name;
    /**
     * 认证的校园网账号
     */
    private String number;
    /**
     * 在本系统中绑定的手机
     */
    private String telephone;
    /**
     * 用户名
     */
    private String username;
    /**
     * 在当前项目中的权限
     1：项目负责人，2：指导老师，3：项目成员
     */
    private int permission;
}
