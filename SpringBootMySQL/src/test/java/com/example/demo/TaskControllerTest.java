package com.example.demo;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.controller.TaskController;
import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TaskControllerTest {
	@Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    public TaskControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTaskById_Found() {
        int id = 1;
        Task task = new Task();
        task.setName("TestName");

        when(taskService.getTaskById(id)).thenReturn(task);

        ResponseEntity<Object> response = taskController.getTaskById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(task, response.getBody());
    }
}
