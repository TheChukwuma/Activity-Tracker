package com.chukwuma.timemanagementapp.serviceImpl;

import com.chukwuma.timemanagementapp.enums.Status;
import com.chukwuma.timemanagementapp.model.Task;
import com.chukwuma.timemanagementapp.model.User;
import com.chukwuma.timemanagementapp.repository.TaskRepository;
import com.chukwuma.timemanagementapp.repository.UserRepository;
import com.chukwuma.timemanagementapp.service.TaskService;
import com.chukwuma.timemanagementapp.utils.DateAndTime;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TaskServiceImpl implements TaskService {
    final
    TaskRepository taskRepo;
    final
    UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepo, UserRepository userRepository) {
        this.taskRepo = taskRepo;
        this.userRepository = userRepository;
    }

    @Override
    public Task save(Task task, Long id) {
        task.setCreatedTime(DateAndTime.getDateAndTime());
        task.setCompletedTime("00.00");
        task.setUpdateTime("00.00");
        Optional<User> user = userRepository.findById(id);
        task.setUser(user.get());
        return taskRepo.save(task);
    }

    @Override
    public Task selectTaskById(Long id) {
        return taskRepo.findById(id).get();
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    @Override
    public List<Task> getAllCompletedTask(String status) {
        return taskRepo.findAllByStatus(status);
    }

    @Override
    public List<Task> getAllUncompletedTask(String status) {
        return taskRepo.findAllByStatus(status);
    }

    @Override
    public Task updateTaskById(Task task, Long id) {
        Task newTask = taskRepo.findById(id).get();
        newTask.setTitle(task.getTitle());
        newTask.setDescription(task.getDescription());
        newTask.setCompletedTime("00.00");
        newTask.setUpdateTime(DateAndTime.getDateAndTime());
        newTask.setStatus(Status.IN_PROGRESS.name());
        newTask.setProgress(task.getProgress());
        return taskRepo.save(newTask);
    }

    @Override
    public String deleteTask(Long id) {
        taskRepo.deleteById(id);
        return "Deleted successfully";
    }

    @Override
    public String deleteAllTasks() {
        taskRepo.deleteAll();
        return "All task deleted successfully";
    }

    @Override
    public void completeTask(Long id) {

    }


}
