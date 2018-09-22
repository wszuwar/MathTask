package com.maths.mathTasks.controller;

import com.maths.mathTasks.config.AdminConfig;
import com.maths.mathTasks.domain.MathTaskDto;
import com.maths.mathTasks.mapper.MathTaskMapper;
import com.maths.mathTasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/mathTask")
public class MathTaskController {



    @Autowired
    private DbService service;

    @Autowired
    private MathTaskMapper mathTaskMapper;



    @RequestMapping(method = RequestMethod.GET, value = "getMathTasks")
    public List<MathTaskDto> getMathTasks(){

       return mathTaskMapper.mapToMathTaskDtoList(service.getAllMathTasks());

    }

    @RequestMapping(method = RequestMethod.GET, value = "getMathTask")
    public MathTaskDto getMathTask(@RequestParam Long mathTaskId) throws MathTaskNotFound{
        return mathTaskMapper.mapToMathTaskDto(service.getMathTask(mathTaskId).orElseThrow(MathTaskNotFound::new));
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "deleteMathTask")
    public void deleteMathTask(@RequestParam Long mathTaskId){
            service.deleteMathTask(mathTaskId);
    }
    @RequestMapping(method = RequestMethod.PUT, value = "updateMathTask")
    public MathTaskDto updateMathTask(@RequestBody MathTaskDto mathTaskDto){
        return mathTaskMapper.mapToMathTaskDto(service.saveMathTask(mathTaskMapper.mapToMathTask(mathTaskDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createMathTask", consumes = APPLICATION_JSON_VALUE)
    public void createMathTask(@RequestBody MathTaskDto mathTaskDto){
        service.saveMathTask(mathTaskMapper.mapToMathTask(mathTaskDto));
    }

}
