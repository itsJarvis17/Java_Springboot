package com.itsjarvis.Spring.Security.controller;

import com.itsjarvis.Spring.Security.model.User;
import com.itsjarvis.Spring.Security.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;

@RestController
public class HelloController {

    @Autowired
    private UserService userService;

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

    @PostMapping("/register")
    public void register(@RequestBody User user) {
        userService.register(user);
    }
}
