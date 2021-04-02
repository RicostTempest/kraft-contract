package com.windsoft.kraft.contract.server.user.service;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.server.user.entity.AuthEntity;

public interface AuthService {
    public JsonResult authUser(AuthEntity entity, Long id);
    public JsonResult getAccount(Long id);
    public JsonResult freeBand(Long id);
    public JsonResult getAccountDetail(Long id);
}
