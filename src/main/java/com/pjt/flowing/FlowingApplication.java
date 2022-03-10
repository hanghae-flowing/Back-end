package com.pjt.flowing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
@Slf4j
@SpringBootApplication
public class FlowingApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowingApplication.class, args);

    }
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @PostConstruct
    public void test(){
        log.info("{}",url);
        log.info("{}",username);
        log.info("{}",password);
    }

}
