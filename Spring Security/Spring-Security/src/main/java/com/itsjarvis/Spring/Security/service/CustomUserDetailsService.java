package com.itsjarvis.Spring.Security.service;

import com.itsjarvis.Spring.Security.dao.UserRepository;
import com.itsjarvis.Spring.Security.model.User;
import com.itsjarvis.Spring.Security.model.UserDetailsImpl;
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
