package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Task;
import com.example.demo.service.InputParameterValidation;
import com.example.demo.service.TaskConstants;
import com.example.demo.service.TaskService;

@RestController
@RequestMapping("/taskManager")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@GetMapping("/tasks")
	public ResponseEntity<List<Task>> getTasks() {
		try {
			List<Task> tasks = taskService.getTasks();
			return new ResponseEntity<>(tasks, HttpStatus.OK);
		} catch (Exception exception) {
			throw exception;
		}
	}

	@GetMapping("/task/{taskId}")
	public ResponseEntity<Object> getTaskById(@PathVariable int taskId) {
		try {
			Task task = taskService.getTaskById(taskId);
			return new ResponseEntity<>(task != null ? task : TaskConstants.NOT_FOUND, HttpStatus.OK);
		} catch (Exception exception) {
			throw exception;
		}
	}

	@PostMapping("/task")
	public ResponseEntity<String> addTask(@RequestBody Task task) {
		try {
			validateInputFields(task);
			taskService.saveTask(task);
			return new ResponseEntity<>(TaskConstants.TASK_SUCCESS, HttpStatus.CREATED);
		} catch (InputParameterValidation inputParameterValidation) {
			throw inputParameterValidation;
		} catch (Exception exception) {
			throw exception;
		}
	}

	@DeleteMapping("/task/{taskId}")
	public ResponseEntity<String> deleteTask(@PathVariable int taskId) {
		try {
			String result = taskService.deleteTask(taskId);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception exception) {
			throw exception;
		}

	}

	@PutMapping("/{taskId}")
	public ResponseEntity<String> updateTask(@PathVariable int taskId, @RequestBody Task updatedTaskDetails) {
		try {
			String result = taskService.updateTask(taskId, updatedTaskDetails);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	private void validateInputFields(Task task) {
		if (null == task.getName() || task.getName().isEmpty() || null == task.getDescription()
				|| task.getDescription().isEmpty() || task.getName().length() > TaskConstants.NAME_LENGTH
				|| task.getDescription().length() > TaskConstants.DESCRIPTION_LENGTH) {
			throw new InputParameterValidation(TaskConstants.INPUT_VALIDATION_FAILED);
		}

	}
}
