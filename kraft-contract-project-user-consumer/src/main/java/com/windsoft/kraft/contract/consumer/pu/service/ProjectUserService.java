package com.windsoft.kraft.contract.consumer.pu.service;

import com.windsoft.kraft.contract.common.utils.JsonResult;

public interface ProjectUserService {
    public JsonResult add(Long userId, Long projectId,Byte permission);
    public JsonResult delete(Long userId, Long projectId);
    public JsonResult findProject(Long id);
    public JsonResult findUser(Long id);
    public JsonResult getProjectList(Long id);

    JsonResult getPermission(Long userId, Long projectId);
}
