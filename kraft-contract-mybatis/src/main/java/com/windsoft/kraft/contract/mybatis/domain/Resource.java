package com.windsoft.kraft.contract.mybatis.domain;

import com.windsoft.kraft.contract.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "kc_resource")
public class Resource extends BaseEntity {
    @Column(name = "url")
    private String url;

    @Column(name = "resource_type_id")
    private Long resourceTypeId;

    /**
     * 资源名称
     */
    @Column(name = "`name`")
    private String name;
}