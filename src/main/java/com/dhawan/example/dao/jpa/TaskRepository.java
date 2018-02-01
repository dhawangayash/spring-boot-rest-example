package com.dhawan.example.dao.jpa;


import com.dhawan.example.domain.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import springfox.documentation.annotations.Cacheable;

import java.util.List;


/**
 * Created by dhawangayash on 1/21/18.
 */

@Component
public interface TaskRepository  extends CrudRepository<Task, Long> {

    //Page<Task> find(Pageable pageable);

/*
    @Query("select * from user_table where uid = ?0")
    List<Task> findByUserId(Long uid);
*/

}
