package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;

@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;

	public Task saveTask(Task task) {
		return taskRepository.save(task);
	}

	public List<Task> getTasks() {
		return taskRepository.findAll();
	}

	public Task getTaskById(int taskId) {
		return taskRepository.findById(taskId).orElse(null);
	}

	public String deleteTask(int taskId) {
		Optional<Task> task = taskRepository.findById(taskId);
		if (!task.isEmpty()) {
			taskRepository.deleteById(taskId);
			return TaskConstants.TASK_REMOVED + taskId;
		}
		return TaskConstants.NOT_FOUND + taskId;
	}
	public String updateTask(int taskId, Task updatedTaskDetails) {
		Optional<Task> existingTask = taskRepository.findById(taskId);

		if (existingTask.isPresent()) {
			Task task = existingTask.get();

			// Update fields
			task.setName(updatedTaskDetails.getName());
			task.setDescription(updatedTaskDetails.getDescription());

			taskRepository.save(task);
			return TaskConstants.TASK_UPDATED + taskId;
		}

		return TaskConstants.NOT_FOUND + taskId;
	}

}
