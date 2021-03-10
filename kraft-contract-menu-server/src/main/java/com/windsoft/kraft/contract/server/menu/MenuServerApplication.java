package com.windsoft.kraft.contract.server.menu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.windsoft.kraft.contract.server.menu.mapper"})
public class MenuServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MenuServerApplication.class, args);
    }
}
