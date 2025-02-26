package com.itsjarvis.JarvisEcomWebsite.controller;

import com.itsjarvis.JarvisEcomWebsite.model.User;
import com.itsjarvis.JarvisEcomWebsite.service.JwtService;
import com.itsjarvis.JarvisEcomWebsite.service.ProductService;
import com.itsjarvis.JarvisEcomWebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated())
            return new ResponseEntity<>(jwtService.generateToken(user.getUsername()), HttpStatus.CREATED);
        else
            return new ResponseEntity<>("Failed", HttpStatus.NOT_FOUND);
    }
}
