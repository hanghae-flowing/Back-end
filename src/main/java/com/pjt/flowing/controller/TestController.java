package com.pjt.flowing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("api/test")
    public String toast(){
        return "HelloWorld";
    }
}
