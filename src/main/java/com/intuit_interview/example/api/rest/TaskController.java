package com.intuit_interview.example.api.rest;


import com.intuit_interview.example.domain.Task;
import com.intuit_interview.example.exception.DataFormatException;
import com.intuit_interview.example.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/taskmanager/v1/tasks")
@Api(tags = "{tasks}")
public class TaskController extends AbstractRestHandler {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a task for a userId",
            notes = "Returns the taskId as a URL resource")
    public void createTask(@RequestBody Task task,
                           HttpServletRequest req, HttpServletResponse res) {
        Task createdTask = this.taskService.createTask(task);
        res.setHeader("Location", req.getRequestURL().append("/").append(createdTask.getTaskId()).toString());
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all tasks.",
                  notes = "The list is paginated. The caller provides the page number and the page size. By default the page size is 100")
    public
    @ResponseBody
    Page<Task> getAllTask(@ApiParam(value = "The page number (zero based counting)", required = true)
                       @RequestParam(value = "task", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                       @ApiParam(value = "The page size", required = true)
                       @RequestParam (value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
                       HttpServletRequest req, HttpServletResponse res)  throws Exception {
        return this.taskService.getAllTasks(page, size);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a single task", notes = "Provide the task Id")
    public
    @ResponseBody
    Task getTask(@ApiParam(value = "The id of the task", required = true)
                 @PathVariable("id") Long taskId,
                 HttpServletRequest req, HttpServletResponse res) throws Exception {
        Task task = this.taskService.getTask(taskId);
        checkResourceFound(task);
        return task;
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update the task resource.",
                    notes = "You have to provide task id in the url and the payload.")
    public void updateTask(@ApiParam(value = "The ID of the existing tasks", required = true)
                           @PathVariable("id") Long taskId, @RequestBody Task task,
                           HttpServletRequest req, HttpServletResponse res) {
        checkResourceFound(this.taskService.getTask(taskId));
        if (taskId != task.getTaskId()) throw new DataFormatException("TaskID doesn't match");
        this.taskService.updateTask(task);
    }


    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a task resource",
                    notes = "You have to provide valid task_id in the url. Once deleted the task cannot be recovered")
    public void deleteTask(@ApiParam(value = "The ID of the existing task resource", required = true)
                            @PathVariable("taskId") Long taskId,
                            HttpServletRequest req, HttpServletResponse res) {
        checkResourceFound(this.taskService.getTask(taskId));
        this.taskService.deleteTask(taskId);
    }
}
