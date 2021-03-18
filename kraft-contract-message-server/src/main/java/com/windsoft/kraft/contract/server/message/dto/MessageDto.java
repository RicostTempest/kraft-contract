package com.windsoft.kraft.contract.server.message.dto;

import lombok.Data;

@Data
public class MessageDto {
    private Long sendId;
    private Long[] acceptId;
    private String content;
    private String title;
    private int type;
}
