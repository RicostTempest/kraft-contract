package com.windsoft.kraft.contract.server.user.mapper;

import com.windsoft.kraft.contract.mybatis.domain.User;
import com.windsoft.kraft.contract.mybatis.mapper.BaseMapper;
import com.windsoft.kraft.contract.server.user.dto.UserListDto;
import com.windsoft.kraft.contract.server.user.query.UserInfoQuery;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    public List<UserListDto> selectUserAuth(@Param("query") UserInfoQuery query);
    public boolean updatePassword(@Param("user") User user);
}