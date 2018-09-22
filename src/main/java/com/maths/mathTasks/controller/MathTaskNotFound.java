package com.maths.mathTasks.controller;

public class MathTaskNotFound extends Exception {
    public MathTaskNotFound(){
        super("MathTask Id Not found");
    }
}
