package com.sbi.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sbi.entity.User;
import com.sbi.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            User userObj = user.get();
            return new org.springframework.security.core.userdetails.User(
                userObj.getUsername(),
                userObj.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(userObj.getRole()))
            );          
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
