package com.windsoft.kraft.contract.mybatis.entity;

import com.windsoft.kraft.contract.mybatis.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;

@Data
public class InfoEntity extends BaseEntity {
    /**
     * 姓名
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 身份证号
     */
    @Column(name = "identification")
    private String identification;

    /**
     * 电话号码
     */
    @Column(name = "telephone")
    private String telephone;

    /**
     * 校园账号登录密码
     */
    @Column(name = "`password`")
    private String password;

    /**
     * 校园账号
     */
    @Column(name = "`number`")
    private String number;
}
