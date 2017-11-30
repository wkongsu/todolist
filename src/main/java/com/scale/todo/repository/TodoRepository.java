package com.scale.todo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.scale.todo.model.Task;

public interface TodoRepository extends MongoRepository<Task, Integer> {

	List<Task> findAll();
	
	Task findById(String id);
	
}
