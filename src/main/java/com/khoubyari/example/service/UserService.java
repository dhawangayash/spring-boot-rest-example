package com.khoubyari.example.service;

import com.khoubyari.example.dao.jpa.TaskRepository;
import com.khoubyari.example.dao.jpa.UserRepository;
import com.khoubyari.example.domain.Task;
import com.khoubyari.example.domain.User;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;

import java.util.List;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    CounterService counterService;

    @Autowired
    GaugeService gaugeService;

    public UserService() {}

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUser(long userId) {
        return userRepository.findOne(userId);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    // TODO get all tasks for a user....
}
