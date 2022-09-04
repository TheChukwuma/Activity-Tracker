package com.chukwuma.timemanagementapp.service;

import com.chukwuma.timemanagementapp.model.Task;

import java.util.List;

public interface TaskService {

    Task save(Task task, Long id);

    Task selectTaskById(Long id);

    List<Task> getAllTasksByUserId(Long user);

    List<Task> selectTaskByStatus(String status);

    List<Task> getAllCompletedTask(Long user);

    List<Task> getAllInProgressTask(Long user);

    List<Task> getAllPendingTask(Long user);

    Task updateTaskById(Task task, Long id);

    String deleteTask(Long id);

    String deleteAllTasks();

    void completeTask(Long id);

    Task startTask(Long id);

    Task endTask(Long id);
}
