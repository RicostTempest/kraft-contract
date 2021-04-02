package com.windsoft.kraft.contract.server.flow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan(basePackages = {"com.windsoft.kraft.contract.server.flow.mapper"})
public class FlowServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlowServerApplication.class, args);
    }
}
