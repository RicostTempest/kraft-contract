package com.windsoft.kraft.contract.server.menu.query;

import com.windsoft.kraft.contract.mybatis.query.BaseQuery;
import lombok.Data;

/**
 * 菜单查询条件
 */
@Data
public class MenuQuery extends BaseQuery {

    /**
     * 菜单标题
     */
    private String title;

}
