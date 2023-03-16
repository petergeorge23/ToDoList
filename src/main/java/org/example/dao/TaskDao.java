package org.example.dao;

import org.example.model.Task;

import java.util.List;

public interface TaskDao {

	List<Task> getTasks();

	void addTask(String task);

	void deleteTask(int id);

	void markTaskAsCompleted(int id);
	
}
