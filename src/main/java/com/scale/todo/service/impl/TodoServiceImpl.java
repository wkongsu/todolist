package com.scale.todo.service.impl;

import java.util.List;

import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scale.todo.model.Task;
import com.scale.todo.repository.TodoRepository;
import com.scale.todo.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	TodoRepository todoRepository;

	@Override
	public List<Task> getAllTasks() throws RuntimeException {
		return todoRepository.findAll();
	}

	@Override
	public Task getTaskById(String id) throws RuntimeException {
		return todoRepository.findById(id);
	}

	@Override
	public void addTaskToList(Task task) throws RuntimeException {

		if (task.getSubject() == null || task.getStatus() == null) {
			throw new IllegalArgumentException("Invalid Paramater");
		}

		todoRepository.insert(task);
	}

	@Override
	public void updateTaskById(String id, Task task) throws RuntimeException {
		Task curTask = todoRepository.findById(id);

		if (curTask == null) {
			throw new NotFoundException("Task not found");
		}

		if (task.getSubject() == null || task.getStatus() == null) {
			throw new IllegalArgumentException("Invalid Paramater");
		}

		curTask.setSubject(task.getSubject());
		curTask.setDetail(task.getDetail());
		curTask.setStatus(task.getStatus());
		todoRepository.save(curTask);
	}

	@Override
	public void updateStatusById(String id, String status) throws RuntimeException {
		Task curTask = todoRepository.findById(id);

		if (curTask == null) {
			throw new NotFoundException("Task not found");
		}

		if (status == null) {
			throw new IllegalArgumentException("Invalid Paramater");
		}

		curTask.setStatus(status);
		todoRepository.save(curTask);
	}

	@Override
	public void deleteTaskFromList(String id) throws RuntimeException {
		Task curTask = todoRepository.findById(id);

		if (curTask == null) {
			throw new NotFoundException("Task not found");
		}

		todoRepository.delete(curTask);
	}

}
