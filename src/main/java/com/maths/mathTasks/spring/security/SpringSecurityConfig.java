package com.maths.mathTasks.spring.security;

import com.maths.mathTasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AdminConfig adminConfig;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser(adminConfig.getAdminName())
                .password(adminConfig.getAdminPassword())
                .roles(adminConfig.getAdminRole());
        auth.inMemoryAuthentication().withUser("User").password("user").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().anyRequest().hasRole("ADMIN").and().httpBasic();
        http.authorizeRequests().anyRequest().hasRole("USER").and().httpBasic();
    }
}
