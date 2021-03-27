package com.windsoft.kraft.contract.consumer.pu.mapper;

import com.windsoft.kraft.contract.consumer.pu.dto.ProjectCardDto;
import com.windsoft.kraft.contract.consumer.pu.dto.UserInfoDto;
import com.windsoft.kraft.contract.mybatis.domain.Project;
import com.windsoft.kraft.contract.mybatis.domain.ProjectUser;
import com.windsoft.kraft.contract.mybatis.mapper.BaseMapper;

import java.util.List;

public interface ProjectUserMapper extends BaseMapper<ProjectUser> {
    public List<Project> selectByUserID(Long id);
    public List<UserInfoDto> selectMemberByProjectId(Long id);
    public List<UserInfoDto> selectAdviserByProjectId(Long id);
    public List<ProjectCardDto> selectProjectCardInfo();
}