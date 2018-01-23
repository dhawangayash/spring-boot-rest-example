package com.intuit_interview.example.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit_interview.example.Application;
import com.intuit_interview.example.api.rest.UserController;
import com.intuit_interview.example.domain.User;
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
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by dhawangayash on 1/22/18.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class UserControllerTest {

    private static final String RESOURCE_LOCATION_PATTERN = "http://localhost/taskmanager/v1/user/1";

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
    public void shouldCreateRetrieveDelete() throws Exception {
        User user = mockUser("shouldCreateRetrieveDelete");
        byte[] jsonUser = toJson(user);

        // Create user
        MvcResult res = mvc.perform(post("/taskmanager/v1/user")
                .content(jsonUser)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(redirectedUrl(RESOURCE_LOCATION_PATTERN))
                .andReturn();
        


    }

    private User mockUser(String prefix) {
        User u = new User();
        u.setAddress("9999 cross street, Mountain View, 94041");
        u.setEmailId("abc@intuit.com");
        u.setPhone("(999)-999-9999");
        u.setTasks(new HashSet<>());
        return u;
    }

    private byte[] toJson(Object o) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsBytes(o);
    }
}
