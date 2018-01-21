package com.khoubyari.example.api.rest;


import com.khoubyari.example.domain.User;
import com.khoubyari.example.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/example/v1/user")
@Api(tags = "{user}")
public class UserController extends AbstractRestHandler {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a user for a userID",
        notes = "Returns the userID as a URL resource")
    public void createUser(@RequestBody User user,
                           HttpServletRequest req, HttpServletResponse res) {
        User createdUser = this.userService.createUser(user);
        res.setHeader("Location", req.getRequestURL().append("/").append(user.getUserId()).toString());
    }

    // TODO setters and link with tasks
}
