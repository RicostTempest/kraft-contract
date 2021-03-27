package com.windsoft.kraft.contract.server.flow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.windsoft.kraft.contract.server.flow"})
public class FlowServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlowServerApplication.class, args);
    }
}
