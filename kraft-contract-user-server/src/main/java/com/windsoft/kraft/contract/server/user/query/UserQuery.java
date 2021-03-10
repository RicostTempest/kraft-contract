package com.windsoft.kraft.contract.server.user.query;

import com.windsoft.kraft.contract.mybatis.query.BaseQuery;
import lombok.Data;

@Data
public class UserQuery extends BaseQuery {
    /**
     * 用户账号
     */
    private String username;

    /**
     * 电话号码
     */
    private String telephone;

    /**
     * 邮箱
     */
    private String email;

}
