package com.dhawan.example.service;

import com.dhawan.example.dao.jpa.TaskRepository;
import com.dhawan.example.domain.Task;
import com.dhawan.example.domain.User;
import com.dhawan.example.exception.NoSuchUserException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;

/**
 * Created by dhawangayash on 1/21/18.
 */

@Service
public class TaskService {
    private static final Logger log = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    CounterService couterService;

    @Autowired
    GaugeService gaugeService;

    public TaskService() {}

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task getTask(long taskId) {
        return taskRepository.findOne(taskId);
    }

    public void updateTask(Task task) {
        taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.delete(id);
    }

    /*
    public Page<Task> getAllTasks(Integer page, Integer size) {
        Page<Task> pageOne = taskRepository.findAll(new PageRequest(page, size));

        if (size > 50) {
            couterService.increment("taskrequest");
        }
        return pageOne;
    }*/
}
