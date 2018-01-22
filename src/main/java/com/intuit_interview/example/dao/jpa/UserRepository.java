package com.intuit_interview.example.dao.jpa;


import com.intuit_interview.example.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    // TODO add task dependency here...
}
