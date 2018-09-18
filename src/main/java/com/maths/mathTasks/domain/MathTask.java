package com.maths.mathTasks.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MathTask {
    private Long id;
    private int a ;
    private  int b;
    private String taskType;

}
