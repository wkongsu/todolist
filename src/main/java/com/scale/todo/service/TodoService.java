package com.scale.todo.service;

import java.util.List;

import com.scale.todo.model.Task;

public interface TodoService {

	public List<Task> getAllTasks() throws RuntimeException;

	public Task getTaskById(String id) throws RuntimeException;

	public void addTaskToList(Task task) throws RuntimeException;

	public void updateTaskById(String id, Task task) throws RuntimeException;

	public void updateStatusById(String id, String status) throws RuntimeException;

	public void deleteTaskFromList(String id) throws RuntimeException;

}
