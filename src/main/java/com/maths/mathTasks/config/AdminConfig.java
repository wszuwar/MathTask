package com.maths.mathTasks.config;

import lombok.Getter;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



public class AdminConfig {


    @Value("${admin.name}")
    private String adminName;

}
