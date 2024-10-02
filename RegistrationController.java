package com.sbi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sbi.entity.User;
import com.sbi.repository.UserRepository;

@RestController
public class RegistrationController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder; // Autowired PasswordEncoder

    @PostMapping("/register/user")
    public User createUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        return userRepository.save(user);
    }
    
      @PostMapping("/register/admin")
      public User createAdmin(@RequestBody User user) {
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
    	user.setRole("ADMIN");
    	return userRepository.save(user);
    }
}
