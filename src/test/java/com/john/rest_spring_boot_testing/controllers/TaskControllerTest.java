package com.john.rest_spring_boot_testing.controllers;

import com.john.rest_spring_boot_testing.exceptions.ResourceNotFoundException;
import com.john.rest_spring_boot_testing.models.Task;
import com.john.rest_spring_boot_testing.services.task.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TaskControllerTest {

    @Autowired
    private TaskRepository taskRepository;

    int getLength(List<Task> tasks) {
        return tasks.size();
    }
    @Test
    void getLengthTasks() {
        Task task1 = new Task("Test 1", "some test description 1", false);
        Task task2 = new Task("Test 2", "some test description 2", true);

        taskRepository.save(task1);
        taskRepository.save(task2);

        List<Task> tasks = taskRepository.findAll();
        assertEquals(2, getLength(tasks));
    }

    @Test
    void saveTask() {
        Task task = new Task("Test 1", "some test description 1", false);
        Task taskSaved = taskRepository.save(task);

        assertNotNull(taskSaved);
    }

    @Test
    void getOneTask() {
        Task testTask = new Task("Test 1", "some test description 1", false);
        taskRepository.save(testTask);
        Optional<Task> taskFound = taskRepository.findById(1L);

        assertNotNull(taskFound);
    }

    @Test
    void deleteTask() {
        Task testTask = new Task("Test 1", "some test description 1", false);
        taskRepository.save(testTask);

        taskRepository.deleteById(testTask.getId());

        //Search the Task Validating if yet exist
        Optional<Task> taskFound = taskRepository.findById(testTask.getId());
        System.out.println(taskFound);

        assertEquals(Optional.empty(),taskFound);
    }

    boolean changeDoneTask (Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The task not exists in the id " + id));
        boolean stateStarted = task.isDone();
        task.setDone(!task.isDone());
        Task updatedTask = taskRepository.save(task);

        if(stateStarted != updatedTask.isDone()){
            return true;
        }else{
            return false;
        }
    }

    @Test
    void updateTaskDone() {
        Task testTask = new Task("Test 1", "some test description 1", false);
        taskRepository.save(testTask);
        assertTrue(changeDoneTask(testTask.getId()));
    }
}