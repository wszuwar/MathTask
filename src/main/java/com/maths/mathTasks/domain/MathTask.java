package com.maths.mathTasks.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "math_Tasks")
public class MathTask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_id")
    private Long id;

    @Column(name = "a")
    private int a ;

    @Column(name = "task_type")
    private String taskType;

    @Column(name = "b")
    private  int b;

    @Column(name = "result")
    private int result;

    @Column(name = "correct")
    private boolean isCorrect;


}
