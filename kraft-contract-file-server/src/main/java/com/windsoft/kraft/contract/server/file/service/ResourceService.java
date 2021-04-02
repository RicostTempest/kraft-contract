package com.windsoft.kraft.contract.server.file.service;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.mybatis.domain.Resource;

import java.util.List;

public interface ResourceService {
    JsonResult addResources(Long projectId, List<Resource> resources);

    JsonResult getResources(Long id);
}
