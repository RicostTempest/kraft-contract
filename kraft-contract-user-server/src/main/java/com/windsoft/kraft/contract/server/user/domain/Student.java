package com.windsoft.kraft.contract.server.user.domain;

import com.windsoft.kraft.contract.server.user.entity.InfoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "kc_student")
public class Student extends InfoEntity {
    /**
     * 所属班级
     */
    @Column(name = "class_id")
    private Long classId;
}