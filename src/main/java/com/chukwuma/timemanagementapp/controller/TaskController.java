package com.chukwuma.timemanagementapp.controller;


import com.chukwuma.timemanagementapp.model.Task;
import com.chukwuma.timemanagementapp.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
public class TaskController {

    TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/index")
    public String homePage(Model model, Model model2){
        model.addAttribute("task", new Task());
        model2.addAttribute("alltasks",taskService.getAllTasks());
        return "index";
    }

    @PostMapping("/save-task")
    public String saveTask(@ModelAttribute("task") Task task, HttpSession session){
        Long id = (Long)session.getAttribute("userId");
        taskService.save(task,id);
        return "redirect:/index";
    }

    @GetMapping("/home")
    public String anotherHome(){
        return "home";
    }

    @GetMapping("/select-task/{id}")
    public String selectTaskById(@PathVariable Long id, @ModelAttribute("oneTask") Model model){
        model.addAttribute("one-task", taskService.selectTaskById(id));
        return "/index";
    }

    @GetMapping("/all-tasks")
    public String getAllTasks(Model model){
        model.addAttribute("tasks",taskService.getAllTasks());
        return "/index";
    }

    @GetMapping("/all-tasks/{status}")
    public String getAllTaskByStatus(@ModelAttribute("selectedTasks") Model model, @PathVariable String status){
        model.addAttribute("selected-tasks", taskService.getAllCompletedTask(status));
        return "/index";
    }

    @GetMapping("/update-task/{id}")
    public String showUpdateTaskPage(@PathVariable Long id, Model model){
        model.addAttribute("task", taskService.selectTaskById(id));
        return "edit-task";
    }

    @PostMapping("/task-update/{id}")
    public String updateTask(@ModelAttribute("task") Task task, @PathVariable Long id){
        taskService.updateTaskById(task, id);
        return "redirect:/index";
    }

    @GetMapping("/delete-task/{id}")
    public String deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ("redirect:/index");
    }

    @GetMapping("/delete-all-task")
    public String deleteAllTasks(){
        taskService.deleteAllTasks();
        return "redirect:/index";
    }

    @GetMapping("/task-schedule")
    public String showAddTasksPage(){
        return "edit-task";
    }

}
