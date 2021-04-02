package com.windsoft.kraft.contract.server.flow.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Data
public class TaskDto implements Serializable {
    private Long assigneeId;
    private String taskId;
    private String processInstanceId;
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private String forName;
    private String forPhone;
    private String forNumber;

    public void setAccount(Map<String,String> account){
        this.forName = account.get("name");
        this.forPhone = account.get("telephone");
        this.forNumber = account.get("number");
    }
}
