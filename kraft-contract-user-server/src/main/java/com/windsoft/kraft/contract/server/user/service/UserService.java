package com.windsoft.kraft.contract.server.user.service;

import com.windsoft.kraft.contract.common.dto.UserDto;
import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.mybatis.domain.User;
import com.windsoft.kraft.contract.mybatis.service.BaseService;
import com.windsoft.kraft.contract.server.user.query.UserInfoQuery;

public interface UserService extends BaseService<User> {
    public JsonResult resetPasswordByIds(Long[] ids);
    public JsonResult searchMember(UserInfoQuery query);
    public JsonResult searchUser(UserDto userDto);
}
