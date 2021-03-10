package com.windsoft.kraft.contract.server.user.entity;

import lombok.Data;

@Data
public class AuthEntity {
    private String number;
    private String password;
    private Byte isStudent;
}
