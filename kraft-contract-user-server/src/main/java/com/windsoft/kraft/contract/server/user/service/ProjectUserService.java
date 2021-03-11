package com.windsoft.kraft.contract.server.user.service;

import com.windsoft.kraft.contract.common.utils.JsonResult;

public interface ProjectUserService {
    public JsonResult add(Long userId, Long projectId);
    public JsonResult delete(Long userId, Long projectId);
}
