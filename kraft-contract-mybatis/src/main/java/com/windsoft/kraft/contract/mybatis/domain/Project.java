package com.windsoft.kraft.contract.mybatis.domain;

import com.windsoft.kraft.contract.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "kc_project")
public class Project extends BaseEntity {
    /**
     * 项目立项编号
     */
    @Column(name = "code")
    private String code;

    /**
     * 当前使用的项目内容
     */
    @Column(name = "content_id")
    private Long contentId;

    /**
     * 标示进度 0：未立项企划阶段 1：刚开题，2：通过中期检查，3：可以结题，4：结题
     */
    @Column(name = "progress")
    private Integer progress;

    /**
     * 项目经费
     */
    @Column(name = "funding")
    private Long funding;

    @Column(name = "`name`")
    private String name;
}