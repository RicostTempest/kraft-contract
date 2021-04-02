package com.windsoft.kraft.contract.server.user.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountDto implements Serializable {
    //校园网ID
    Long id;
    String name;
    String number;
    String telephone;

}
