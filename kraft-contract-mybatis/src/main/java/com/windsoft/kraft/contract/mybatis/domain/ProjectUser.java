package com.windsoft.kraft.contract.mybatis.domain;

import com.windsoft.kraft.contract.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper=true)
@Table(name = "kc_project_user")
public class ProjectUser extends BaseEntity {
    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 项目id
     */
    @Column(name = "project_id")
    private Long projectId;

    /**
     * 在当前项目中的权限
1：项目负责人，2：指导老师，3：项目成员
     */
    @Column(name = "permission", insertable = false)
    private Byte permission;
}