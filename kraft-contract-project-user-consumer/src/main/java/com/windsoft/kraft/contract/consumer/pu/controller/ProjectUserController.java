package com.windsoft.kraft.contract.consumer.pu.controller;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.consumer.pu.fegin.ProjectServer;
import com.windsoft.kraft.contract.consumer.pu.fegin.UserServer;
import com.windsoft.kraft.contract.consumer.pu.service.ProjectUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pu")
public class ProjectUserController {
    @Autowired
    private ProjectServer projectServer;
    @Autowired
    private UserServer userServer;
    @Autowired
    private ProjectUserService projectUserService;

    @GetMapping("find/project/{userId}")
    public JsonResult findProject(@PathVariable("userId") Long id){
        return projectUserService.findProject(id);
    }

    @PostMapping("connect/{userId}/{projectId}")
    public JsonResult connect(@PathVariable("userId") Long userId,@PathVariable("projectId") Long projectId){
        int userCode = userServer.infoUser(userId).getCode();
        int projectCode = projectServer.infoProject(projectId).getCode();
        if (userCode == 0 && projectCode == 0){
            return projectUserService.add(userId, projectId);
        }else if (userCode == 400 || projectCode == 400){
            return JsonResult.error("服务正在维护");
        }
        return JsonResult.error("用户或项目不存在");
    }

    @DeleteMapping("disconnect/{userId}/{projectId}")
    public JsonResult disconnect(@PathVariable("userId") Long userId,@PathVariable("projectId") Long projectId){
        return projectUserService.delete(userId, projectId);
    }
}
