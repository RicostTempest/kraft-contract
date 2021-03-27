package com.windsoft.kraft.contract.server.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.windsoft.kraft.contract.server.file.mapper")
public class FileServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileServerApplication.class, args);
    }
}
