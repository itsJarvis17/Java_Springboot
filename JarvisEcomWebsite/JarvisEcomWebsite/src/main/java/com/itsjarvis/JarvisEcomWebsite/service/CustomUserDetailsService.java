package com.itsjarvis.JarvisEcomWebsite.service;


import com.itsjarvis.JarvisEcomWebsite.model.User;
import com.itsjarvis.JarvisEcomWebsite.model.UserDetailsImpl;
import com.itsjarvis.JarvisEcomWebsite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user==null) {
            System.out.println("User Not Found !");
            throw new UsernameNotFoundException("User Not Found !");
        }
        return new UserDetailsImpl(user);
    }
}
