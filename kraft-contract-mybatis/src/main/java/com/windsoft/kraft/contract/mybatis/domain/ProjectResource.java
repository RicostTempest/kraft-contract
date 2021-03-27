package com.windsoft.kraft.contract.mybatis.domain;

import com.windsoft.kraft.contract.mybatis.entity.BaseEntity;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Table(name = "kc_project_resource")
public class ProjectResource extends BaseEntity {
    @Column(name = "project_id")
    private Long projectId;

    /**
     * 对应资源
     */
    @Column(name = "resource_id")
    private Long resourceId;
}