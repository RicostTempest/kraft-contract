package com.windsoft.kraft.contract.server.user.mapper;

import com.windsoft.kraft.contract.mybatis.domain.UserAuthentication;
import com.windsoft.kraft.contract.mybatis.mapper.BaseMapper;
import com.windsoft.kraft.contract.server.user.dto.AccountDto;

public interface UserAuthenticationMapper extends BaseMapper<UserAuthentication> {
    public AccountDto selectAccount(Long id, Byte isStudent);
}