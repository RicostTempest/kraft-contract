package com.windsoft.user;

import com.windsoft.kraft.contract.mybatis.domain.Project;
import com.windsoft.kraft.contract.server.user.UserServerApplication;
import com.windsoft.kraft.contract.server.user.mapper.ProjectUserMapper;
import com.windsoft.kraft.contract.server.user.service.ProjectUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserServerApplication.class)
public class ProjectUserTest {
    @Autowired
    ProjectUserService projectUserService;

    @Autowired
    ProjectUserMapper projectUserMapper;

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
}
