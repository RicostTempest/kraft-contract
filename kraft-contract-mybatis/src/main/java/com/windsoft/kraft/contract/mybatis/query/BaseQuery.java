package com.windsoft.kraft.contract.mybatis.query;

import lombok.Data;

/**
 * 查询对象基类
 */
@Data
public class BaseQuery {
    /**
     * 页码
     */
    private Integer page;

    /**
     * 每页数
     */
    private Integer limit;
}
