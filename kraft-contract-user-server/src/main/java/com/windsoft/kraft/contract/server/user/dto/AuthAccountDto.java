package com.windsoft.kraft.contract.server.user.dto;

import lombok.Data;

@Data
public class AuthAccountDto {
    private Long id;
    private String account;
    private String qq;
    private String wechat;
    private Byte isStudent;
}
