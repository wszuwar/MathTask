package com.maths.mathTasks.spring.security;

import com.maths.mathTasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Autowired
    AdminConfig adminConfig;

    @Bean
    @Override
    public UserDetailsService userDetailsService(){
        UserDetails admin =
               User.withDefaultPasswordEncoder()
                .username(adminConfig.getAdminName())
                .password(adminConfig.getAdminPassword())
                .roles(adminConfig.getAdminRole())
                .build();
        return new InMemoryUserDetailsManager(admin);
    }
}
