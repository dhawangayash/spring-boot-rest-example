package com.khoubyari.example.dao.jpa;

import com.khoubyari.example.domain.Task;

import java.util.List;

public interface TaskObjectRepositoryCustom {

    List<Task> findByUserId(Long uuid);

}
