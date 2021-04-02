package com.windsoft.kraft.contract.server.project.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.mybatis.domain.Project;
import com.windsoft.kraft.contract.mybatis.query.BaseQuery;
import com.windsoft.kraft.contract.mybatis.service.impl.BaseServiceImpl;
import com.windsoft.kraft.contract.server.project.mapper.ProjectMapper;
import com.windsoft.kraft.contract.server.project.query.ProjectQuery;
import com.windsoft.kraft.contract.server.project.service.ProjectService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ProjectServiceImpl extends BaseServiceImpl<ProjectMapper, Project> implements ProjectService {
    @Override
    public JsonResult getList(BaseQuery query) {
        Condition condition = new Condition(Project.class);
        Example.Criteria criteria = condition.createCriteria();
        ProjectQuery projectQuery = (ProjectQuery) query;
        //项目名称
        if (!StringUtils.isEmpty(projectQuery.getName())) {
            criteria.andLike("name","%"+projectQuery.getName()+"%");
        }
        //项目代码
        if (!StringUtils.isEmpty(projectQuery.getCode())) {
            criteria.andLike("code","%"+projectQuery.getCode()+"%");
        }
        //项目名称
        if (projectQuery.getProgress() != null) {
            criteria.andEqualTo("progress",projectQuery.getProgress());
        }

        PageHelper.startPage(query.getPage(),query.getLimit());
        List<Project> projects = baseMapper.selectByCondition(condition);
        PageInfo<Project> pageInfo = new PageInfo<Project>(projects);
        return JsonResult.success(pageInfo);
    }

    @Override
    public JsonResult getListByUserID(Long id){
        return JsonResult.success(baseMapper.selectByUserID(id));
    }

    @Override
    public JsonResult selectProject(Project project) {
        Condition condition = new Condition(Project.class);
        Example.Criteria criteria = condition.createCriteria();
        //项目编号
        if (!StringUtils.isEmpty(project.getCode())) {
            criteria.andEqualTo("code",project.getCode());
        }
        //项目名称
        if (!StringUtils.isEmpty(project.getName())) {
            criteria.andEqualTo("name",project.getName());
        }
        project = baseMapper.selectOneByExample(condition);
        if (project != null){
            return JsonResult.success(project);
        }
        return JsonResult.error("项目不存在");
    }

    @Override
    public JsonResult passProject(Project project) {
        baseMapper.updateProjcetInfo(project);
        return JsonResult.success();
    }
}
