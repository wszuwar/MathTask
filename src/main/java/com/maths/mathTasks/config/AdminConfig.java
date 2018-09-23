package com.maths.mathTasks.config;

import lombok.Getter;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@Getter
public class AdminConfig {



    @Value("${security.user.name}")
    private String adminName;

    @Value("${security.user.password}")
    private String adminPassword;

    @Value(("${security.user.role}"))
    private String adminRole;

}
