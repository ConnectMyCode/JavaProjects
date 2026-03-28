package com.supporttriage.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supporttriage.security.SecurityUtil;

@RestController
public class TestController {

    @GetMapping("/api/test")
    public String test() {
    	String email = SecurityUtil.getCurrentUserEmail();
    	
        return "Logged in as: " + email;
    }
}