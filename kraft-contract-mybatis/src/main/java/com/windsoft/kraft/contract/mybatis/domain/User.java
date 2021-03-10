package com.windsoft.kraft.contract.mybatis.domain;

import com.windsoft.kraft.contract.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "kc_user")
public class User extends BaseEntity {
    /**
     * 电话
     */
    @Column(name = "telephone")
    private String telephone;

    @Column(name = "email")
    private String email;

    @Column(name = "`password`")
    private String password;

    /**
     * 用户名：唯一不可重复不可修改
     */
    @Column(name = "username")
    private String username;

    /**
     * 昵称
     */
    @Column(name = "nickname")
    private String nickname;

    /**
     * 用户头像
     */
    @Column(name = "avatar")
    private String avatar;
}