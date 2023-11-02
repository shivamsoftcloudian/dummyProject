package com.softclouds.dummyproject.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("hello/")
public class HelloRestController {
    private static final Logger logger = LoggerFactory.getLogger(HelloRestController.class);

    @GetMapping("user")
    public String helloUser() {
        logger.info("Calling User...");
        return "Hello User";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("admin")
    public String helloAdmin() {
        return "Hello Admin";
    }


}