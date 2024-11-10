package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class TaskConstants {
	public static String TASK_SUCCESS = "Task created successfully";
	public static String INPUT_VALIDATION_FAILED = "Input parameter validaton failed";
	public static String TASK_REMOVED = "Task removed : ";
	public static String TASK_UPDATED = "Task updated : ";
	public static String NOT_FOUND = "No Task Found : ";
	public static int NAME_LENGTH = 20;
	public static int DESCRIPTION_LENGTH = 100;
}
