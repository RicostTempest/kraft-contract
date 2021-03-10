package com.windsoft.kraft.contract.server.user.service;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.mybatis.service.BaseService;
import com.windsoft.kraft.contract.mybatis.domain.User;

public interface UserService extends BaseService<User> {
    public JsonResult resetPasswordByIds(Long[] ids);
}
