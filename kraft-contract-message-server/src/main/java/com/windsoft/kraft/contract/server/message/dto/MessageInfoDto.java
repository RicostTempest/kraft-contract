package com.windsoft.kraft.contract.server.message.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class MessageInfoDto implements Serializable {
    private Long id;

    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private Byte status;

    private Byte type;

    private Long userId;

    private Long contentId;

    private String username;
}
