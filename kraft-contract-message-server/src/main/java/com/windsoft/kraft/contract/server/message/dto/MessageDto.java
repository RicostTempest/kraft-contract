package com.windsoft.kraft.contract.server.message.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessageDto implements Serializable {
    private Long sendId;
    private Long[] acceptId;
    private String content;
    private String title;
    private int type;
}
