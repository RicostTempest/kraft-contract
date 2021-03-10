package com.windsoft.kraft.contract.mybatis.domain;


import com.windsoft.kraft.contract.mybatis.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.List;

@Data
@Table(name = "kc_menu")
public class Menu extends BaseEntity {
    /**
     * 父级ID
     */
    @Column(name = "pid")
    private Long pid;

    /**
     * 菜单标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 图标
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 菜单路径
     */
    @Column(name = "`path`")
    private String path;

    /**
     * 菜单组件
     */
    @Column(name = "component")
    private String component;

    /**
     * 目标
     */
    @Column(name = "target")
    private String target;

    /**
     * 权限标识
     */
    @Column(name = "permission")
    private String permission;

    /**
     * 类型：1目录 2菜单 3节点
     */
    @Column(name = "`type`")
    private Integer type;

    /**
     * 是否显示：1显示 2不显示
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * 备注
     */
    @Column(name = "note")
    private String note;

    /**
     * 显示顺序
     */
    @Column(name = "sort")
    private Short sort;

    /**
     * 目录子节点
     */
    private List<Menu> children;
}