package com.scale.todo.rest;

import java.util.List;

import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.scale.todo.model.Task;
import com.scale.todo.service.TodoService;

@RestController
@RequestMapping(value = "/todo")
public class TodoRest {

	@Autowired
	TodoService todoService;

	@RequestMapping(value = "/tasks", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Task>> getAllTasks() {
		try {
			List<Task> tasks = todoService.getAllTasks();
			return ResponseEntity.status(HttpStatus.OK).body(tasks);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@RequestMapping(value = "/tasks/{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Task> getTaskById(@PathVariable("id") String id) {

		try {
			Task task = todoService.getTaskById(id);
			if (task != null) {
				return ResponseEntity.status(HttpStatus.OK).body(task);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@RequestMapping(value = "/tasks", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Void> addTaskToList(@RequestBody Task task) {
		try {
			todoService.addTaskToList(task);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@RequestMapping(value = "/tasks/{id}", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<Void> updateTaskById(@PathVariable("id") String id, @RequestBody Task task) {
		try {
			todoService.updateTaskById(id, task);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@RequestMapping(value = "/tasks/status/{id}", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<Void> updateTaskStatusById(@PathVariable("id") String id,
			@RequestBody String status) {
		try {
			todoService.updateStatusById(id, status);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@RequestMapping(value = "/tasks/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<Void> deleteTaskFromList(@PathVariable("id") String id) {
		try {
			todoService.deleteTaskFromList(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
