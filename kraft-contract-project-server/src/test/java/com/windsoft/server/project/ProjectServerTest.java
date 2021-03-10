package com.windsoft.server.project;

import com.windsoft.kraft.contract.mybatis.query.BaseQuery;
import com.windsoft.kraft.contract.server.project.ProjectServerApplication;
import com.windsoft.kraft.contract.server.project.domain.Project;
import com.windsoft.kraft.contract.server.project.mapper.ProjectMapper;
import com.windsoft.kraft.contract.server.project.service.ProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectServerApplication.class)
public class ProjectServerTest {
    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    ProjectService projectService;

    @Test
    public void selectTest(){
        List<Project> projects  = projectMapper.selectAll();
        projects.forEach(item -> System.out.println(item));
        projectService.getList(new BaseQuery());
    }

}
