package com.itsjarvis.JarvisEcomWebsite.controller;

import com.itsjarvis.JarvisEcomWebsite.model.User;
import com.itsjarvis.JarvisEcomWebsite.service.ProductService;
import com.itsjarvis.JarvisEcomWebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;


    @GetMapping("/")
    public ResponseEntity<?> findAllProducts() {
        return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try{
            return userService.register(user);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("User already taken "+e.getMostSpecificCause().getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
