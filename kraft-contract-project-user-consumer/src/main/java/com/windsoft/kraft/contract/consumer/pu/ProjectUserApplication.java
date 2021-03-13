package com.windsoft.kraft.contract.consumer.pu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan(basePackages = {"com.windsoft.kraft.contract.consumer.pu.mapper"})
public class ProjectUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectUserApplication.class, args);
    }
}
