package com.pjt.flowing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

<<<<<<< HEAD
@EnableScheduling // 스프링 부트에서 스케줄러가 작동하게 합니다.
=======
import javax.annotation.PostConstruct;
import java.util.TimeZone;

>>>>>>> 0f197d0f4505e4513befc160e7073872fa875e1f
@SpringBootApplication
@EnableJpaAuditing // 시간 자동 변경이 가능하도록 합니다.
public class FlowingApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowingApplication.class, args);
    }

    @PostConstruct
    public void setup(){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }
}
