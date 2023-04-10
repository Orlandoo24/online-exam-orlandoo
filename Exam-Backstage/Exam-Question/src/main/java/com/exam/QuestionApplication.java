package com.exam;

import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.exam.mapper")
public class QuestionApplication {

    public static void main(String[] args) {
        LogFactory.useCustomLogging(StdOutImpl.class);
        SpringApplication.run(QuestionApplication.class, args);
    }

}

