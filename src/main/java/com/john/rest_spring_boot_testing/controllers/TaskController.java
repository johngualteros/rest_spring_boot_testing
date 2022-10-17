package com.john.rest_spring_boot_testing.controllers;

import com.john.rest_spring_boot_testing.exceptions.ResourceNotFoundException;
import com.john.rest_spring_boot_testing.models.Task;
import com.john.rest_spring_boot_testing.services.task.TaskRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    //Get All Tasks EndPoint
    @Operation(summary = "Get all tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "request successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Task.class)) }),
            @ApiResponse(responseCode = "404", description = "tasks not founds",
                    content = @Content) })
    @GetMapping("/tasks")
    public List<Task> getTasks(){
        return taskRepository.findAll();
    }

    //Save One Task EndPoint
    @Operation(summary = "Save One Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Task Saved Successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Task.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content) })
    @PostMapping("/tasks")
    public Task saveTask(@RequestBody Task task){
        return taskRepository.save(task);
    }

    //Get One Task By Id EndPoint
    @Operation(summary = "Get One Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The Task Found Is",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Task.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content) })
    @GetMapping("/tasks/{id}")
    public Optional<Task> getOneTask(@PathVariable Long id){
        try{
            Optional<Task> taskFound = taskRepository.findById(id);
            return taskFound;
        }catch(Exception e){
            return Optional.empty();
        }
    }
    //Delete Task By Id EndPoint
    @Operation(summary = "Delete One Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Task Deleted Successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Task.class)) }),
            @ApiResponse(responseCode = "404", description = "The Task Not exist or Not Found",
                    content = @Content) })
    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable Long id){
        taskRepository.deleteById(id);
    }

    //Change Done By Id Endpoint
    @Operation(summary = "Update One Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task Done Changed Successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Task.class)) }),
            @ApiResponse(responseCode = "404", description = "The Task Not exist or Not Found",
                    content = @Content) })
    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTaskDone(@PathVariable Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The task not exists in the id " + id));
        task.setDone(!task.isDone());
        Task taskUpdated = taskRepository.save(task);

        return ResponseEntity.ok(task);
    }
}
