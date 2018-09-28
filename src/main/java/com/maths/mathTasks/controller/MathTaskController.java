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
@RequestMapping("/v1")
public class MathTaskController {


    @Autowired
    private DbService service;

    @Autowired
    private MathTaskMapper mathTaskMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/mathTasks")
    public List<MathTaskDto> getMathTasks() {

        return mathTaskMapper.mapToMathTaskDtoList(service.getAllMathTasks());

    }

    @RequestMapping(method = RequestMethod.GET, value = "/mathTasks/{mathTaskId}")
    public MathTaskDto getMathTask(@PathVariable Long mathTaskId) throws MathTaskNotFound {
        return mathTaskMapper.mapToMathTaskDto(service.getMathTask(mathTaskId).orElseThrow(MathTaskNotFound::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/mathTasks/{mathTaskId}")
    public void deleteMathTask(@PathVariable Long mathTaskId) {
        service.deleteMathTask(mathTaskId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/mathTasks")
    public MathTaskDto updateMathTask(@RequestBody MathTaskDto mathTaskDto) {
        return mathTaskMapper.mapToMathTaskDto(service.saveMathTask(mathTaskMapper.mapToMathTask(mathTaskDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/mathTasks", consumes = APPLICATION_JSON_VALUE)
    public void createMathTask(@RequestBody MathTaskDto mathTaskDto) {
        service.saveMathTask(mathTaskMapper.mapToMathTask(mathTaskDto));
    }

}
