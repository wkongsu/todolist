package com.scale.todo.task;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.scale.todo.model.Task;
import com.scale.todo.rest.TodoRest;
import com.scale.todo.service.TodoService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TodoApplicationTest {

	@MockBean
	TodoService todoService;

	@Autowired
	TodoRest todoRest;

	@Before
	public void setUp() throws Exception {
		Mockito.when(todoService.getTaskById("1")).thenReturn(new Task("id", "subject", "detail", "status"));
		Mockito.when(todoService.getAllTasks()).thenReturn(new ArrayList<Task>());
	}

	@Test
	public void getTask() throws Exception {
		ResponseEntity<Task> response = todoRest.getTaskById("1");
		Task task = response.getBody();
		Assert.assertEquals("id", task.getId());
		Assert.assertEquals("subject", task.getSubject());
		Assert.assertEquals("detail", task.getDetail());
		Assert.assertEquals("status", task.getStatus());
	}

	@Test
	public void listAllTask() throws Exception {
		ResponseEntity<List<Task>> responseEntity = todoRest.getAllTasks();
		Assert.assertEquals(0, responseEntity.getBody().size());
	}

	@Test
	public void deleteTask() throws Exception {
		ResponseEntity<Void> responseEntity = todoRest.deleteTaskFromList("1");
	}

	@Test
	public void addTaskToList() throws Exception {
		ResponseEntity<Void> responseEntity = todoRest.addTaskToList(new Task("111", "do homework", "english homework", "waiting"));
	}

	@Test
	public void updateTask() throws Exception {
		ResponseEntity<Void> responseEntity = todoRest.updateTaskById("111", new Task("do homework", "thai homework", "doing"));
	}
}
