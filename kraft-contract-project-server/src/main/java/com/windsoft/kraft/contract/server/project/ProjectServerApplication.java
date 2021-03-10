package com.windsoft.kraft.contract.server.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.windsoft.kraft.contract.server.project.service.*", "com.windsoft.kraft.contract.server.project.mapper"})
public class ProjectServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectServerApplication.class, args);
    }
}
