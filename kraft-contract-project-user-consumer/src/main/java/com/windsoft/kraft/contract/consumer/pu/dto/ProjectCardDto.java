package com.windsoft.kraft.contract.consumer.pu.dto;

import lombok.Data;

@Data
public class ProjectCardDto {
    private Long id;
    private String name;
    private String code;
    private Integer progress;
    private Long funding;
    private Long leaderId;
    private String leaderName;
    private Integer exist;
}
