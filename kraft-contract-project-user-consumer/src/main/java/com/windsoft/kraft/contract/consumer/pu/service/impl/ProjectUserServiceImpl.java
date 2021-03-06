package com.windsoft.kraft.contract.consumer.pu.service.impl;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.consumer.pu.dto.ProjectCardDto;
import com.windsoft.kraft.contract.consumer.pu.dto.UserInfoDto;
import com.windsoft.kraft.contract.consumer.pu.mapper.ProjectUserMapper;
import com.windsoft.kraft.contract.consumer.pu.service.ProjectUserService;
import com.windsoft.kraft.contract.mybatis.domain.Project;
import com.windsoft.kraft.contract.mybatis.domain.ProjectUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ProjectUserServiceImpl implements ProjectUserService {

    @Autowired
    private ProjectUserMapper projectUserMapper;

    @Override
    public JsonResult add(Long userId, Long projectId,Byte permission) {
        Condition condition = new Condition(ProjectUser.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("projectId", projectId)
                .andEqualTo("userId", userId);
        ProjectUser pu = projectUserMapper.selectOneByExample(condition);
        if(pu == null){
            pu = new ProjectUser();
            pu.setUserId(userId);
            pu.setProjectId(projectId);
            pu.setPermission(permission);
            projectUserMapper.insert(pu);
            return JsonResult.success();
        }else {
            return JsonResult.error("用户已存在项目中");
        }
    }

    @Override
    public JsonResult delete(Long userId, Long projectId) {
        Condition condition = new Condition(ProjectUser.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("projectId", projectId)
                .andEqualTo("userId", userId);
        projectUserMapper.deleteByCondition(condition);
        return JsonResult.success();
    }

    @Override
    public JsonResult findProject(Long id) {
        return JsonResult.success(projectUserMapper.selectByUserID(id));
    }

    @Override
    public JsonResult findUser(Long id) {
        List<UserInfoDto> userInfoDtos = projectUserMapper.selectMemberByProjectId(id);
        userInfoDtos.addAll(projectUserMapper.selectAdviserByProjectId(id));
        if (userInfoDtos.size() == 0) {
            return JsonResult.error("项目中没有成员");
        }
        return JsonResult.success(userInfoDtos);
    }

    @Override
    public JsonResult getProjectList(Long id) {
        List<ProjectCardDto> projectCardDtos = projectUserMapper.selectProjectCardInfo();
        List<Project> projects = projectUserMapper.selectByUserID(id);
        projectCardDtos.forEach(card -> {
            card.setExist(0);
            projects.forEach(project -> {
                if (project.getId().equals(card.getId())){
                    card.setExist(1);
                }
            });
        });
        return JsonResult.success(projectCardDtos);
    }

    @Override
    public JsonResult getPermission(Long userId, Long projectId){
        Condition condition = new Condition(ProjectUser.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("projectId", projectId)
                .andEqualTo("userId", userId);
        ProjectUser projectUser = projectUserMapper.selectOneByExample(condition);
        if (projectUser != null){
            return JsonResult.success(projectUser.getPermission());
        }
        return JsonResult.success("用户不在当前项目中");
    }
}
