package com.chukwuma.timemanagementapp.service;

import com.chukwuma.timemanagementapp.model.Task;

import java.util.List;

public interface TaskService {

    Task save(Task task);

    Task selectTaskById(Long id);

    List<Task> getAllTasks();

    List<Task> getAllCompletedTask();

    List<Task> getAllUncompletedTask();

    Task updateTaskById(Task task, Long id);

    String deleteTask(Long id);



}
