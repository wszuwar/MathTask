package com.maths.mathTasks.repository;

import com.maths.mathTasks.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
