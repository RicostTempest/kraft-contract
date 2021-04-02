package com.windsoft.kraft.contract.server.project.mapper;

import com.windsoft.kraft.contract.mybatis.domain.Project;
import com.windsoft.kraft.contract.mybatis.mapper.BaseMapper;

import java.util.List;

public interface ProjectMapper extends BaseMapper<Project> {
    public List<Project> selectByUserID(Long id);
    public void updateProjcetInfo(Project project);
}