package com.windsoft.kraft.contract.server.user.service.impl;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.mybatis.domain.Project;
import com.windsoft.kraft.contract.mybatis.domain.ProjectUser;
import com.windsoft.kraft.contract.mybatis.domain.User;
import com.windsoft.kraft.contract.server.user.mapper.ProjectUserMapper;
import com.windsoft.kraft.contract.server.user.service.ProjectUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

@Service
public class ProjectUserServiceImpl implements ProjectUserService {

    @Autowired
    private ProjectUserMapper projectUserMapper;

    @Override
    public JsonResult add(Long userId, Long projectId) {
        User user = projectUserMapper.selectUserById(userId);
        Project project = projectUserMapper.selectProjectById(projectId);
        if (user != null && project != null){
            Condition condition = new Condition(ProjectUser.class);
            Example.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("projectId", projectId)
                    .andEqualTo("userId", userId);
            ProjectUser pu = projectUserMapper.selectOneByExample(condition);
            if(pu == null){
                pu = new ProjectUser();
                pu.setUserId(userId);
                pu.setProjectId(projectId);
                projectUserMapper.insert(pu);
                return JsonResult.success();
            }else {
                return JsonResult.error("用户已存在项目中");
            }
        }
        return JsonResult.error("用户或项目不存在");
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
}
