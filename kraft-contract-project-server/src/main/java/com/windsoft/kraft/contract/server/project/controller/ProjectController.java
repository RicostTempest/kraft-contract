package com.windsoft.kraft.contract.server.project.controller;

import com.alibaba.fastjson.JSON;
import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.common.utils.RandomUtils;
import com.windsoft.kraft.contract.mybatis.domain.Project;
import com.windsoft.kraft.contract.server.project.query.ProjectQuery;
import com.windsoft.kraft.contract.server.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 76069
 */
@RestController
@RequestMapping("project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("find")
    public JsonResult index(ProjectQuery projectQuery){
        return projectService.getList(projectQuery);
    }

    @PutMapping("edit")
    public JsonResult update(@RequestBody Project entity) {
        return projectService.update(entity);
    }

    @PostMapping("add")
    public JsonResult add(@RequestBody Project entity){
        return projectService.add(entity);
    }

    @DeleteMapping("delete/{projectIds}")
    public JsonResult delete(@PathVariable("projectIds") Long[] ids){
        return projectService.deleteByIds(ids);
    }

    @GetMapping("info/{projectId}")
    public JsonResult info(@PathVariable("projectId") Long id){
        return projectService.info(id);
    }

    @GetMapping("find/{userId}")
    public JsonResult findProject(@PathVariable("userId") Long id){
        return projectService.getListByUserID(id);
    }

    @GetMapping("exist")
    public JsonResult projectExist(@RequestParam("project") String entity){
        Project project = JSON.parseObject(entity, Project.class);
        return projectService.selectProject(project);
    }

    @PostMapping("init/{projectId}")
    public JsonResult initProject(@PathVariable("projectId") Long id,@RequestBody Project entity){
        entity.setProgress(1);
        entity.setCode(RandomUtils.getNumber(12));
        return projectService.passProject(entity);
    }

}
