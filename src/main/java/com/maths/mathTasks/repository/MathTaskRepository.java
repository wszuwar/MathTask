package com.maths.mathTasks.repository;

import com.maths.mathTasks.domain.MathTask;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MathTaskRepository extends CrudRepository<MathTask, Long> {

    @Override
    List<MathTask> findAll();


    List<MathTask> findAllByTaskLvl(Integer integer);

    @Override
    MathTask findOne(Long aLONG);

    @Override
    MathTask save(MathTask mathTask);

    Optional<MathTask>findById(Long id);

    @Override
    void delete(Long mathTaskId);
}
