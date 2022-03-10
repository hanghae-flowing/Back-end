package com.pjt.flowing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class FlowingApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowingApplication.class, args);
    }

    @PostConstruct
    public void setup(){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }
}
