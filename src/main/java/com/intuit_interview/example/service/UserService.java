package com.intuit_interview.example.service;

import com.intuit_interview.example.dao.jpa.UserRepository;
import com.intuit_interview.example.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Service;


/**
 * Created by dhawangayash on 1/21/18.
 */

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
