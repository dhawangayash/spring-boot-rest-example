package com.intuit_interview.example.api.rest;


import com.intuit_interview.example.domain.User;
import com.intuit_interview.example.exception.DataFormatException;
import com.intuit_interview.example.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/taskmanager/v1/user")
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
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a single user", notes = "Provide the user id")
    public
    @ResponseBody
    User getUser(@ApiParam(value = "The id of the user", required = true)
                 @PathVariable("id") Long userId,
                 HttpServletRequest req, HttpServletResponse res) throws Exception {
        User user = this.userService.getUser(userId);
        checkResourceFound(user);
        return user;
    }


    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update the user resource.",
            notes = "You have to provide user id in the url and the payload.")
    public void updateTask(@ApiParam(value = "The ID of the existing users", required = true)
                           @PathVariable("id") Long userId, @RequestBody User user,
                           HttpServletRequest req, HttpServletResponse res) {
        checkResourceFound(this.userService.getUser(userId));
        if (userId != user.getUserId()) throw new DataFormatException("UserID doesn't match");
        this.userService.updateUser(user);
    }

}
