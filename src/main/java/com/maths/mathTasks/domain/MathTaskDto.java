package com.maths.mathTasks.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MathTaskDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("a")
    private int a;

    @JsonProperty("taskType")
    private String taskType;

    @JsonProperty("b")
    private int b;

    @JsonProperty("result")
    private int result;

    @JsonProperty("isCorrect")
    private boolean isCorrect;

    @JsonProperty("taskLvl")
    private int taskLvl;

    @JsonProperty("reply")
    private int reply;

}
