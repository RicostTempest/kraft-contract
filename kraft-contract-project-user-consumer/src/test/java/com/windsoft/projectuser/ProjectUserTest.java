package com.windsoft.projectuser;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.consumer.pu.ProjectUserApplication;
import com.windsoft.kraft.contract.consumer.pu.fegin.ProjectServer;
import com.windsoft.kraft.contract.consumer.pu.fegin.UserServer;
import com.windsoft.kraft.contract.consumer.pu.mapper.ProjectUserMapper;
import com.windsoft.kraft.contract.consumer.pu.service.ProjectUserService;
import com.windsoft.kraft.contract.mybatis.domain.Project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
        projectUserService.add(2L,5L);
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
}
