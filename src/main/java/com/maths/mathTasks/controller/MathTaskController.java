package com.maths.mathTasks.controller;

import com.maths.mathTasks.config.UserConfig;
import com.maths.mathTasks.domain.MathTaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/mathTask")
public class MathTaskController {

    @Autowired
    private UserConfig user;



    @RequestMapping(method = RequestMethod.GET, value = "getMathTasks")
    public List<MathTaskDto> getMathTasks(String name){
        if (name.equals(user.getName())) ;
        return new ArrayList<>();

    }

    @RequestMapping(method = RequestMethod.GET, value = "getMathTask")
    public MathTaskDto getMathTask(Long mathTaskId){
        return new MathTaskDto(1L, 1, "+", 1);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "deleteMathTask")
    public void deleteMathTask(Long mathTaskId){

    }
    @RequestMapping(method = RequestMethod.PUT, value = "updateMathTask")
    public MathTaskDto updateMathTask(MathTaskDto mathTaskDto){
        return new MathTaskDto(1l, 2, "+",2);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createMathTask")
    public void createMathTask(MathTaskDto mathTaskDto){

    }

}
