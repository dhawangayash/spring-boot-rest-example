package com.khoubyari.example.dao.jpa;


import com.khoubyari.example.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    // TODO add task dependency here...
}
