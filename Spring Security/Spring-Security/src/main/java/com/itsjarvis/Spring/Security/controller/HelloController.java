package com.itsjarvis.Spring.Security.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;

@RestController
public class HelloController {
    @GetMapping("/")
    public String home() {
        return "Hello World";
    }

    @GetMapping("/csrf-token")
    private static CsrfToken getCsrfToken(HttpServletRequest rq) {
        return (CsrfToken) rq.getAttribute("_csrf");
    }
    @PostMapping("/about")
    public String aboutSession(HttpServletRequest rq) {
        return rq.getSession().getId();
    }
}
