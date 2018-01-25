package com.dhawan.example.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.dhawan.example.Application;
import com.dhawan.example.api.rest.UserController;
import com.dhawan.example.domain.Task;
import com.dhawan.example.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by dhawangayash on 1/22/18.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class UserControllerTest {

    private static final String RESOURCE_LOCATION_PATTERN = "http://localhost/taskmanager/v1/user/";
    private static final String RESOURCE_LOCATION_TASK_PATTERN = "http://localhost/taskmanager/v1/tasks/";

    @InjectMocks
    UserController controller;

    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    public void shouldCreateRetrieve() throws Exception {
        User user = mockUser("shouldCreateRetrieveDelete");
        byte[] jsonUser = toJson(user);

        // Create user
        MvcResult res = mvc.perform(post("/taskmanager/v1/user")
                .content(jsonUser)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(redirectedUrl(RESOURCE_LOCATION_PATTERN + "1"))
                .andReturn();

        String[] parts = res.getResponse().getRedirectedUrl().split("/");
        long userId = Long.valueOf(parts[parts.length - 1]);

        // Read user
        mvc.perform(get("/taskmanager/v1/user/" + userId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is((int) userId)))
                .andExpect(jsonPath("$.address", is(user.getAddress())))
                .andExpect(jsonPath("$.emailId", is(user.getEmailId())))
                .andExpect(jsonPath("$.phone", is(user.getPhone())));

    }

    @Test
    public void shouldCreateTaskAndRetrieveTask() throws Exception {
        User user = mockUser("shouldCreateTaskAndRetrieveTask");
        byte[] jsonUser = toJson(user);

        // create user
        MvcResult res = mvc.perform(
                post("/taskmanager/v1/user")
                        .content(jsonUser)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(redirectedUrl(RESOURCE_LOCATION_PATTERN + "2"))
                .andReturn();

        String[] parts = res.getResponse().getRedirectedUrl().split("/");
        long userId = Long.valueOf(parts[parts.length - 1]);

        // Read user
        MvcResult response = mvc.perform(get("/taskmanager/v1/user/" + userId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is((int) userId)))
                .andExpect(jsonPath("$.address", is(user.getAddress())))
                .andExpect(jsonPath("$.emailId", is(user.getEmailId())))
                .andExpect(jsonPath("$.phone", is(user.getPhone())))
                .andReturn();

        Task task = mockTask("shouldCreateTaskAndRetrieveTask", userId);
        System.out.println(">>>>>>>>>>>>" + task);
        byte[] jsonTask = toJson(task);

        System.out.println(">>>>>>>>>>>>" +userId);

        MvcResult resultTask = mvc.perform(
                post("/taskmanager/v1/tasks")
                .content(jsonTask)
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                 .andExpect(redirectedUrl(RESOURCE_LOCATION_TASK_PATTERN
                        + "1"))
                .andReturn();

        parts = resultTask.getResponse().getRedirectedUrl().split("/");
        long task_id = Long.valueOf(parts[parts.length - 1]);


        // System.out.println(">>>>>>>>>>>>" + resultTask.getResponse().getHeaderNames().stream().collect(Collectors.joining()));
        // System.out.println(">>>>>>>>>>>>" + resultTask.getResponse().getRedirectedUrl());


        // parts = resultTask.getResponse().getRedirectedUrl().split("/");
        // long taskId = Long.valueOf(parts[parts.length - 1]);
        mvc.perform(
            get("/taskmanager/v1/tasks/" + task_id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.taskId", is((int)task_id)));

    }


    private Task mockTask(String prefix, long userId) {
        Task task = new Task();
        task.setUser_id(userId);
        task.setDueTime("2018-01-23_14:00");
        task.setTaskObjectStoreURL("string");
        return task;
    }


    private User mockUser(String prefix) {
        User u = new User();
        u.setAddress(prefix + " 9999 cross street, Mountain View, 94041");
        u.setEmailId(prefix + "abc@intuit.com");
        u.setPhone("(999)-999-9999");
        u.setTasks(new HashSet<>());
        return u;
    }

    private byte[] toJson(Object o) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsBytes(o);
    }
}
