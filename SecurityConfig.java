package com.sbi.config;

import java.security.AuthProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.sbi.service.MyUserDetailsService;

@Configuration
public class SecurityConfig {
	
   @Autowired
    private final MyUserDetailsService myUserDetailsService;

    // Constructor injection
    public  SecurityConfig (MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return myUserDetailsService;
    }
    @Bean
   public AuthenticationProvider authenticationProvider() {
       	 DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
       	 daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
       	 daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
       	 return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
  
        return httpSecurity
        		 .csrf().disable() // Disable CSRF protection
        		
        		.authorizeHttpRequests(auth -> auth
                    .requestMatchers("/home","register/**").permitAll()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .requestMatchers("/user/**").hasRole("USER")
                    .anyRequest().authenticated())
                .formLogin(form -> form.permitAll())
                .build();
    }
}
