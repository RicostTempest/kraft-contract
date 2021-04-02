package com.windsoft.kraft.contract.consumer.pu.dto;

import com.windsoft.kraft.contract.mybatis.domain.Resource;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProjectDto implements Serializable {
    private String name;
    private String content;
    private Long[] members;
    private Long[] adviser;
    private Resource[] files;
}
