package com.maths.mathTasks.mapper;

import com.maths.mathTasks.domain.MathTask;
import com.maths.mathTasks.domain.MathTaskDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MathTaskMapper {

    public MathTask mapToMathTask(final MathTaskDto mathTaskDto) {
        return new MathTask(
                mathTaskDto.getId(),
                mathTaskDto.getA(),
                mathTaskDto.getTaskType(),
                mathTaskDto.getB(),
                mathTaskDto.getResult(),
                mathTaskDto.isCorrect(),
                mathTaskDto.getTaskLvl(),
                mathTaskDto.getReply()
        );
    }

    public MathTaskDto mapToMathTaskDto(final MathTask mathTask) {
        return new MathTaskDto(
                mathTask.getId(),
                mathTask.getA(),
                mathTask.getTaskType(),
                mathTask.getB(),
                mathTask.getResult(),
                mathTask.isCorrect(),
                mathTask.getTaskLvl(),
                mathTask.getReply()
        );
    }

    public List<MathTaskDto> mapToMathTaskDtoList(final List<MathTask> mathTaskList) {
        return mathTaskList.stream()
                .map(m -> new MathTaskDto(m.getId(), m.getA(), m.getTaskType(), m.getB(), m.getResult(), m.isCorrect(), m.getTaskLvl(), m.getReply()))
                .collect(Collectors.toList());
    }
}