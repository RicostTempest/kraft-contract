package com.windsoft.kraft.contract.consumer.pu.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProjectCardDto implements Serializable {
    private Long id;
    private String name;
    private String code;
    private Integer progress;
    private BigDecimal funding;
    private Long leaderId;
    private String leaderName;
    private Integer exist;
}
