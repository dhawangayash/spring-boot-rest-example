package com.dhawan.example.dao.jpa;

import com.dhawan.example.domain.Task;

import java.util.List;

public interface TaskObjectRepositoryCustom {

    List<Task> findByUserId(Long uuid);

}
