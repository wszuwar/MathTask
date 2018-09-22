package com.maths.mathTasks.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MathTaskDto {
    private Long id;
    private int a ;
    private String taskType;
    private  int b;
    private int result;
    private boolean isCorrect;

}
