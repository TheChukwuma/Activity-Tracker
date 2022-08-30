package com.chukwuma.timemanagementapp.controller;


import com.chukwuma.timemanagementapp.model.Task;
import com.chukwuma.timemanagementapp.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class TaskController {

    TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/save-task")
    public String saveTask(Task task){
        System.out.println(task);
        taskService.save(task);
        System.out.println("hello chuks");
        return "redirect:/index";
    }

    @GetMapping("/select-task/{id}")
    public String selectTaskById(@PathVariable Long id, @ModelAttribute("oneTask") Model model){
        model.addAttribute("one-task", taskService.selectTaskById(id));
        return "/index";
    }

    @GetMapping("/all-tasks")
    public String getAllTasks(@ModelAttribute("tasks") Model model){
        model.addAttribute("tasks",taskService.getAllTasks());
        return "/index";
    }

    @GetMapping("/all-tasks/{status}")
    public String getAllTaskByStatus(@ModelAttribute("selectedTasks") Model model, @PathVariable String status){
        model.addAttribute("selected-tasks", taskService.getAllCompletedTask(status));
        return "/index";
    }

    @PutMapping("/update-task/{id}")
    public String updateTask(@ModelAttribute("updateTask") Task task, @PathVariable Long id){
        taskService.updateTaskById(task, id);
        return "redirect:/index";
    }

    @DeleteMapping("/delete-task/{id}")
    public String deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ("redirect:/index");
    }

    @DeleteMapping("/delete-all-task")
    public String deleteAllTasks(){
        taskService.deleteAllTasks();
        return "redirect:/login";
    }

    @GetMapping("/task-schedule")
    public String showAddTasksPage(){
        return "new-task";
    }

}
