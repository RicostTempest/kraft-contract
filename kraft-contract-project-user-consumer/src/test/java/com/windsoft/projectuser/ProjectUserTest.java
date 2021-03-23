package com.windsoft.projectuser;

import com.alibaba.fastjson.JSON;
import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.consumer.pu.ProjectUserApplication;
import com.windsoft.kraft.contract.consumer.pu.dto.UserInfoDto;
import com.windsoft.kraft.contract.consumer.pu.fegin.ProjectServer;
import com.windsoft.kraft.contract.consumer.pu.fegin.UserServer;
import com.windsoft.kraft.contract.consumer.pu.mapper.ProjectUserMapper;
import com.windsoft.kraft.contract.consumer.pu.service.ProjectUserService;
import com.windsoft.kraft.contract.mybatis.domain.Message;
import com.windsoft.kraft.contract.mybatis.domain.Project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectUserApplication.class)
public class ProjectUserTest {
    @Autowired
    ProjectUserService projectUserService;
    @Autowired
    ProjectUserMapper projectUserMapper;
    @Autowired
    private ProjectServer projectServer;
    @Autowired
    private UserServer userServer;

    @Test
    public void addTest(){
//        projectUserService.add(2L,5L);
        Message message = new Message();
        message.setId(1L);
        message.setId(null);
        System.out.println(message);
    }

    @Test
    public void deleteTest(){
        projectUserService.delete(2L,5L);
    }

    @Test
    public void test(){
        List<Project> projects = projectUserMapper.selectByUserID(1L);
        projects.forEach(item -> System.out.println(item));
    }

    @Test
    public void projectServerTest(){
        JsonResult jsonResult = userServer.infoUser(1L);
        JsonResult jsonResult1 = projectServer.infoProject(1L);
        System.out.println(1);
    }

    @Test
    public void selectMember(){
        List<UserInfoDto> members = projectUserMapper.selectMemberByProjectId(1L);
        List<UserInfoDto> advisers = projectUserMapper.selectAdviserByProjectId(1L);
        members.addAll(advisers);
        members.forEach(item -> System.out.println(item));
    }

    @Test
    public void permission(){
        Project project = new Project();
        project.setCode("201913001014");
        System.out.println(JSON.toJSONString(project));
        JsonResult result = projectServer.projectExist(JSON.toJSONString(project));
        if (result.getCode() == 0){
            Object obj = result.getData();
            project = JSON.parseObject(JSON.toJSONString(result.getData()), Project.class);
            Long id = 1L;
            result = projectUserService.findUser(id);

            if (result.getCode() != 0){
                System.out.println(result);
                return;
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
                System.out.println(JsonResult.error("用户不在项目中，操作无效"));
                return;
            }
            data.put("entities",infoDtos);
            String json = JSON.toJSONString(JsonResult.success(data, "权限确认完成"));;
            System.out.println(json);
        }
    }
}
