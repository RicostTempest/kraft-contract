package com.windsoft.kraft.contract.server.project.service;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.mybatis.domain.Project;
import com.windsoft.kraft.contract.mybatis.service.BaseService;

public interface ProjectService extends BaseService<Project> {
    public JsonResult getListByUserID(Long id);
}
