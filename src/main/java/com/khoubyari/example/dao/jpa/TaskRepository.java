package com.khoubyari.example.dao.jpa;


import com.khoubyari.example.domain.Hotel;
import com.khoubyari.example.domain.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Repository can be used to delegate CRUD operations against the data source: http://goo.gl/P1J8QH
 */

public interface TaskRepository  extends PagingAndSortingRepository<Task, Long>  {
    Page<Task> findAll(Pageable pageable);

    List<Task> findByTaskId(Long taskId);

/*
    @Query("select * from user_table where uid = ?0")
    List<Task> findByUserId(Long uid);
*/


}
