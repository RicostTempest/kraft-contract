package com.windsoft.kraft.contract.server.user.mapper;

import com.windsoft.kraft.contract.mybatis.domain.Project;
import com.windsoft.kraft.contract.mybatis.domain.ProjectUser;
import com.windsoft.kraft.contract.mybatis.domain.User;
import com.windsoft.kraft.contract.mybatis.mapper.BaseMapper;

import java.util.List;

public interface ProjectUserMapper extends BaseMapper<ProjectUser> {
    public List<Project> selectByUserID(Long id);
    public Project selectProjectById(Long id);
    public User selectUserById(Long id);
}