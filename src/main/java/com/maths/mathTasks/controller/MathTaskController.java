package com.maths.mathTasks.controller;

import com.maths.mathTasks.domain.MathTaskDto;
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

    @RequestMapping(method = RequestMethod.GET, value = "getMathTasksNikodem")
    public List<MathTaskDto> getMathTasksNikodem(){
        return new ArrayList<>();
    }
    @RequestMapping(method = RequestMethod.GET, value = "getMathTasksMarcel")
    public List<MathTaskDto> getMathTasksMarcel(){
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getMathTask")
    public MathTaskDto getMathTask(Long mathTaskId){
        return new MathTaskDto(1L, 1, 1);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "deleteMathTask")
    public void deleteMathTask(Long mathTaskId){

    }
    @RequestMapping(method = RequestMethod.PUT, value = "updateMathTask")
    public MathTaskDto updateMathTask(MathTaskDto mathTaskDto){
        return new MathTaskDto(1l, 2, 3);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createMathTask")
    public void createMathTask(MathTaskDto mathTaskDto){

    }

}
