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
    private String taskType;
    private  int b;


}
