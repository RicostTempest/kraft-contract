package com.windsoft.kraft.contract.server.user.query;

import com.windsoft.kraft.contract.mybatis.query.BaseQuery;
import lombok.Data;

@Data
public class UserInfoQuery extends BaseQuery {
    /**
     * 用户认证账号的真实姓名
     */
    private String name;
    /**
     * 认证的校园网账号
     */
    private String number;
    /**
     * 在本系统中绑定的手机
     */
    private String telephone;

    private Integer isStudent;
}
