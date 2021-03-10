package com.windsoft.kraft.contract.server.project.query;

import com.windsoft.kraft.contract.mybatis.query.BaseQuery;
import lombok.Data;

@Data
public class ProjectQuery extends BaseQuery {
    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目代码
     */
    private String code;

    /**
     * 项目进程
     */
    private Integer progress;
}
