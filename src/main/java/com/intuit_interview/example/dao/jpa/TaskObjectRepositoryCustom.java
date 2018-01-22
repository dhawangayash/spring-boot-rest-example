package com.intuit_interview.example.dao.jpa;

import com.intuit_interview.example.domain.Task;

import java.util.List;

public interface TaskObjectRepositoryCustom {

    List<Task> findByUserId(Long uuid);

}
