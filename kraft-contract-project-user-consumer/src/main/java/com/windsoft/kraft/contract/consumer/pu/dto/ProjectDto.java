package com.windsoft.kraft.contract.consumer.pu.dto;

import com.windsoft.kraft.contract.mybatis.domain.Resource;
import lombok.Data;

@Data
public class ProjectDto {
    private String name;
    private String content;
    private Long[] members;
    private Long[] adviser;
    private Resource[] files;
}
