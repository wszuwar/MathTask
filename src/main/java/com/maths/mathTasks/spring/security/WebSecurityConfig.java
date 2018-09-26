package com.maths.mathTasks.spring.security;

import com.maths.mathTasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .authorizeRequests()
                .antMatchers("/v1/mathTask/getMathTasks").authenticated()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/home")
                .permitAll();
    }

    @Autowired
    AdminConfig adminConfig;

   @Autowired
    public void userConfigure(AuthenticationManagerBuilder authenticationManagerBuilder)throws Exception{
       authenticationManagerBuilder.inMemoryAuthentication()
               .withUser(adminConfig.getAdminName())
               .password(adminConfig.getAdminPassword())
               .authorities(adminConfig.getAdminRole());
   }
}
