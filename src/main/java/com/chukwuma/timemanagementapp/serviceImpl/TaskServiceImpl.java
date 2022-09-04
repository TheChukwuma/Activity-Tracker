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
        task.setStartedTime("");
        task.setCompletedTime("");
        task.setUpdateTime("");
        Optional<User> user = userRepository.findById(id);
        task.setUser(user.get());
        return taskRepo.save(task);
    }

    @Override
    public Task selectTaskById(Long id) {
        return taskRepo.findById(id).get();
    }

    @Override
    public List<Task> getAllTasksByUserId(Long user) {
        return taskRepo.findAllByUserId(user);
    }

    @Override
    public List<Task> selectTaskByStatus(String status) {
        return taskRepo.findAllByStatus(status);
    }

    @Override
    public List<Task> getAllCompletedTask(Long user) {
        return taskRepo.findAllByUserIdWhereStatusIsCompleted(user);
    }

    @Override
    public List<Task> getAllInProgressTask(Long user) {
        return taskRepo.findAllByUserIdWhereStatusIsInProgress( user);
    }

    @Override
    public List<Task> getAllPendingTask(Long user) {
        return taskRepo.findAllByUserIdWhereStatusIsPending(user);
    }

    @Override
    public Task updateTaskById(Task task, Long id) {
        Task newTask = taskRepo.findById(id).get();
        if (!newTask.getStartedTime().equals("")){
            newTask.setTitle(task.getTitle());
            newTask.setDescription(task.getDescription());
            newTask.setStatus(task.getStatus());
            newTask.setUpdateTime(DateAndTime.getDateAndTime());
            newTask.setCompletedTime("");
        }
        //newTask.setProgress(task.getProgress());
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

    @Override
    public Task startTask(Long id) {
        Task task = selectTaskById(id);
        task.setStartedTime(DateAndTime.getDateAndTime());
        task.setStatus(Status.IN_PROGRESS.name());
        return taskRepo.save(task);
    }

    @Override
    public Task endTask(Long id) {
        Task task = selectTaskById(id);
        if(task.getStatus().equals(Status.IN_PROGRESS.name())) {
            task.setCompletedTime(DateAndTime.getDateAndTime());
            task.setStatus(Status.COMPLETED.name());
        }
        return taskRepo.save(task);
    }



}
