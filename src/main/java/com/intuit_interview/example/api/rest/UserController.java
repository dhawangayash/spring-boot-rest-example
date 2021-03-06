package com.intuit_interview.example.api.rest;


import com.intuit_interview.example.domain.User;
import com.intuit_interview.example.exception.DataFormatException;
import com.intuit_interview.example.exception.PhoneNumberNotValidException;
import com.intuit_interview.example.exception.UserEmailNotValidException;
import com.intuit_interview.example.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

/**
 * Created by dhawangayash on 1/21/18.
 */


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
    @ApiOperation(value = "Create a user for a userID",
        notes = "Returns the userID as a URL resource")
    public void createUser(@RequestBody User user,
                           HttpServletRequest req, HttpServletResponse res) {
        try {
            checkResourceFound(user);
            User createdUser = this.userService.createUser(user);
            res.setStatus(CREATED.value());
            res.setHeader("Location", req.getRequestURL().append("/").append(createdUser.getUserId()).toString());
        } catch (UserEmailNotValidException  ex) {
            res.setHeader("Errors", "invalid email id");
            res.setStatus(BAD_REQUEST.value());
        } catch (PhoneNumberNotValidException ex) {
            res.setHeader("Errors", "invalid phone number");
            res.setStatus(HttpStatus.BAD_REQUEST.value());
        }
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
        try {
            checkResourceFound(this.userService.getUser(userId));
            this.userService.updateUser(user);
            res.setStatus(HttpStatus.NO_CONTENT.value());
        } catch (UserEmailNotValidException  ex) {
            res.setHeader("Errors", "invalid email id");
            res.setStatus(HttpStatus.BAD_REQUEST.value());
        } catch (PhoneNumberNotValidException ex) {
            res.setHeader("Errors", "invalid phone number");
            res.setStatus(HttpStatus.BAD_REQUEST.value());
        }
        if (userId != user.getUserId()) throw new DataFormatException("UserID doesn't match");
    }

}
