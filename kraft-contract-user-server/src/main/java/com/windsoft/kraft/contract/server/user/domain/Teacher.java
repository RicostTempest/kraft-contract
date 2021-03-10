package com.windsoft.kraft.contract.server.user.domain;

import com.windsoft.kraft.contract.server.user.entity.InfoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "kc_teacher")
public class Teacher extends InfoEntity {
    @Column(name = "college_id")
    private Integer collegeId;

    @Column(name = "professional_title_id")
    private Long professionalTitleId;
}