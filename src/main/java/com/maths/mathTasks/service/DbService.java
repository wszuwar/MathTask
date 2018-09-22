package com.maths.mathTasks.service;

import com.maths.mathTasks.domain.MathTask;
import com.maths.mathTasks.repository.MathTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {

    @Autowired
    private MathTaskRepository repository;

    public List<MathTask> getAllMathTasks(){
        return repository.findAll();
    }

    public MathTask getMathTaskById(final Long id){
        return repository.findOne(id);
    }
    public MathTask saveMathTask(final MathTask mathTask){
        return repository.save(mathTask);
    }
    public Optional<MathTask> getMathTask(final Long id){
        return repository.findById(id);
    }

    public void deleteMathTask(final Long mathTaskId){
        repository.delete(mathTaskId);
    }

}
