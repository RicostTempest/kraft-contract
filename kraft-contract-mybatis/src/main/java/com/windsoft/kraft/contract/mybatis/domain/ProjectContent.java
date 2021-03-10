package com.windsoft.kraft.contract.mybatis.domain;

import com.windsoft.kraft.contract.mybatis.entity.BaseEntity;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Table(name = "kc_project_content")
public class ProjectContent extends BaseEntity {
    @Column(name = "content")
    private String content;

    /**
     * 外键绑定列，指向项目
     */
    @Column(name = "project_id")
    private Long projectId;

    /**
     * 可用标记
     */
    @Column(name = "mark")
    private Byte mark;
}