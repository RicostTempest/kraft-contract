package com.windsoft.kraft.contract.mybatis.domain;

import com.windsoft.kraft.contract.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper=true)
@Table(name = "kc_user_authentication")
public class UserAuthentication extends BaseEntity {
    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 教师或学生id
     */
    @Column(name = "authentication_id")
    private Long authenticationId;

    /**
     * 判定身份 1学生 0教师
     */
    @Column(name = "is_student")
    private Byte isStudent;
}