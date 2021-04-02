package com.windsoft.kraft.contract.consumer.pu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.windsoft.kraft.contract.common.utils.CommonUtils;
import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.consumer.pu.dto.ProjectDto;
import com.windsoft.kraft.contract.consumer.pu.dto.UserInfoDto;
import com.windsoft.kraft.contract.consumer.pu.fegin.FileServer;
import com.windsoft.kraft.contract.consumer.pu.fegin.FlowServer;
import com.windsoft.kraft.contract.consumer.pu.fegin.ProjectServer;
import com.windsoft.kraft.contract.consumer.pu.fegin.UserServer;
import com.windsoft.kraft.contract.consumer.pu.service.ProjectUserService;
import com.windsoft.kraft.contract.mybatis.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("pu")
public class ProjectUserController {
    @Autowired
    private ProjectServer projectServer;
    @Autowired
    private UserServer userServer;
    @Autowired
    private FileServer fileServer;
    @Autowired
    private FlowServer flowServer;
    @Autowired
    private ProjectUserService projectUserService;

    @GetMapping("find/project/{userId}")
    public JsonResult findProjectWithUser(@PathVariable("userId") Long id){
        return projectUserService.findProject(id);
    }

    @GetMapping("find/user/{projectId}")
    public JsonResult findUserInProject(@PathVariable("projectId") Long id){
        return projectUserService.findUser(id);
    }

    @PostMapping("connect/{userId}/{projectId}")
    public JsonResult connect(@PathVariable("userId") Long userId,@PathVariable("projectId") Long projectId){
        int userCode = userServer.infoUser(userId).getCode();
        int projectCode = projectServer.infoProject(projectId).getCode();
        if (userCode == 0 && projectCode == 0){
            return projectUserService.add(userId, projectId, (byte) 3);
        }else if (userCode == 400 || projectCode == 400){
            return JsonResult.error("服务正在维护");
        }
        return JsonResult.error("用户或项目不存在");
    }

    @DeleteMapping("disconnect/{userId}/{projectId}")
    public JsonResult disconnect(@PathVariable("userId") Long userId,@PathVariable("projectId") Long projectId){
        return projectUserService.delete(userId, projectId);
    }

    @GetMapping("permission/{userId}")
    public JsonResult permissionUserInProject(@PathVariable("userId") Long id, @RequestParam("project")String entity){

        JsonResult result = projectServer.projectExist(entity);
        if (result.getCode() != 0) {
            return result;
        }
        Project project = JSON.parseObject(JSON.toJSONString(result.getData()), Project.class);
        result = projectUserService.findUser(id);
        if (result.getCode() != 0){
            return result;
        }
        List<UserInfoDto> infoDtos = (List<UserInfoDto>) projectUserService.findUser(id).getData();
        Map<String, Object> data = new HashMap<>();
        data.put("permission",0);
        infoDtos.forEach(item ->{
            if (item.getId().equals(id)){
               data.put("permission",item.getPermission());
            }
        });
        if (data.get("permission").equals(0)){
            return JsonResult.error("用户不在项目中，操作无效");
        }
        data.put("entities",infoDtos);
        return JsonResult.success(data,"权限确认完成");
    }

    @PostMapping("project/create/{userId}")
    public JsonResult createProject(@PathVariable("userId") Long id, @RequestBody String json){
        Map map = (Map) JSONObject.parse(json);
        ProjectDto projectDto = JSON.parseObject((String) map.get("json"), ProjectDto.class);
        Project project = new Project();
        project.setFunding(0L);
        project.setName(projectDto.getName());
        project.setProgress(0);
        JsonResult result = projectServer.add(project);
        if(result.getCode() == 0 ){
            project = JSON.parseObject(JSON.toJSONString(result.getData()), Project.class);
            projectUserService.add(id,project.getId(), (byte) 1);
            for (int i = 0; i < projectDto.getAdviser().length; i++) {
                projectUserService.add(projectDto.getAdviser()[i], project.getId(), (byte) 2);
            }
            for (int i = 0; i < projectDto.getMembers().length; i++) {
                projectUserService.add(projectDto.getMembers()[i],project.getId(), (byte) 3);
            }
            String s = JSON.toJSONString(projectDto.getFiles());
            result = fileServer.resourceSave(project.getId(),s);
            if(result.getCode() == 0){
                Map<String,Object> assigneeMap = new HashMap<>();
                assigneeMap.put("leader",id.toString());
                assigneeMap.put("adviser", CommonUtils.longArrayToString(projectDto.getAdviser(),","));
                assigneeMap.put("college","17");
                assigneeMap.put("school","18");
                flowServer.createProjectFlow(project.getId(),JSON.toJSONString(assigneeMap));
                return JsonResult.success();
            }
        }
        return result;
    }

    @GetMapping("project/list/{userId}")
    public JsonResult projectList(@PathVariable("userId") Long id){
        return projectUserService.getProjectList(id);
    }
}
