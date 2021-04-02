package com.windsoft.kraft.contract.server.flow.configure;

import org.activiti.engine.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActivitiConfigure {
    @Bean
    public ProcessEngine processEngine(){
        return ProcessEngines.getDefaultProcessEngine();
    }
    @Bean
    public RuntimeService runtimeService(){
        return processEngine().getRuntimeService();
    }
    @Bean
    public TaskService taskService(){
        return processEngine().getTaskService();
    }
    @Bean
    public RepositoryService repositoryService(){
        return processEngine().getRepositoryService();
    }
    @Bean
    public HistoryService historyService(){
        return processEngine().getHistoryService();
    }
}
