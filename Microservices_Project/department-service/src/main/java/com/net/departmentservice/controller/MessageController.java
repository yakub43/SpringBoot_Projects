package com.net.departmentservice.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

@RefreshScope
@RestController
public class MessageController {

    @Value("${spring.boot.message}")
    private String message;
    @GetMapping("message")
    public String message(){
        return message;
    }
}
