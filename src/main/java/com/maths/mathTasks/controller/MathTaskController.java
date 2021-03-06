package com.maths.mathTasks.controller;

import com.maths.mathTasks.domain.MathTaskDto;
import com.maths.mathTasks.mapper.MathTaskMapper;
import com.maths.mathTasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v2")
public class MathTaskController {


    @Autowired
    private DbService service;

    @Autowired
    private MathTaskMapper mathTaskMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/mathTasks")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public List<MathTaskDto> getMathTasks() {

        return mathTaskMapper.mapToMathTaskDtoList(service.getAllMathTasks());
    }
    @RequestMapping(method = RequestMethod.GET, value = "/mathTasks/{mathTaskLvl}")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public List<MathTaskDto> getMathTasksForLvl(@RequestParam (defaultValue = "2") Integer mathTaskLvl){
        return mathTaskMapper.mapToMathTaskDtoList(service.getAllMathTasksByTaskLvl(mathTaskLvl));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/mathTasks/{mathTaskId}")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public MathTaskDto getMathTask(@PathVariable Long mathTaskId) throws MathTaskNotFound {
        return mathTaskMapper.mapToMathTaskDto(service.getMathTask(mathTaskId).orElseThrow(MathTaskNotFound::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/mathTasks/{mathTaskId}")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public void deleteMathTask(@PathVariable Long mathTaskId) {
        service.deleteMathTask(mathTaskId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/mathTasks")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public MathTaskDto updateMathTask(@RequestBody MathTaskDto mathTaskDto) {
        return mathTaskMapper.mapToMathTaskDto(service.saveMathTask(mathTaskMapper.mapToMathTask(mathTaskDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/mathTasks", consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public void createMathTask(@RequestBody MathTaskDto mathTaskDto) {
        service.saveMathTask(mathTaskMapper.mapToMathTask(mathTaskDto));
    }

}
