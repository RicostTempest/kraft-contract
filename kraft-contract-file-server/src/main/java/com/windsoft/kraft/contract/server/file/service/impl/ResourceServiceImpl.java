package com.windsoft.kraft.contract.server.file.service.impl;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.mybatis.domain.ProjectResource;
import com.windsoft.kraft.contract.mybatis.domain.Resource;
import com.windsoft.kraft.contract.server.file.mapper.ProjectResourceMapper;
import com.windsoft.kraft.contract.server.file.mapper.ResourceMapper;
import com.windsoft.kraft.contract.server.file.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private ProjectResourceMapper projectResourceMapper;

    @Override
    public JsonResult addResources(Long projectId, List<Resource> resources){
        ProjectResource projectResource = new ProjectResource();
        resources.forEach(item -> {
            resourceMapper.insert(item);
            projectResource.setId(null);
            projectResource.setProjectId(projectId);
            projectResource.setResourceId(item.getId());
            projectResourceMapper.insert(projectResource);
        });
        return JsonResult.success();
    }

    @Override
    public JsonResult getResources(Long id) {
        return JsonResult.success(resourceMapper.selectByProjectId(id));
    }
}
