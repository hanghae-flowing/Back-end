package com.pjt.flowing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HealthController {
    @GetMapping("/health")
    public String healthCheck(){

        //return "I'm still alive~!!!";
        return LocalDateTime.now().toString();
    }
}
