package com.dhawan.example.dao.jpa;


import com.dhawan.example.domain.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * Created by dhawangayash on 1/21/18.
 */

public interface TaskRepository  extends PagingAndSortingRepository<Task, Long>  {
    Page<Task> findAll(Pageable pageable);

    // List<Task> findByTaskId(Long taskId);

/*
    @Query("select * from user_table where uid = ?0")
    List<Task> findByUserId(Long uid);
*/


}
