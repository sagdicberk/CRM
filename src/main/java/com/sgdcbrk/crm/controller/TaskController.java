package com.sgdcbrk.crm.controller;

import com.sgdcbrk.crm.business.abstracts.TaskService;
import com.sgdcbrk.crm.model.task.Task;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;


    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable long id) {
        Task task = taskService.getTask(id);
        return ResponseEntity.ok(task);
    }


    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        taskService.addTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable long id, @RequestBody Task taskDetails) {
        taskService.updateTask(id, taskDetails);
        return ResponseEntity.ok(taskDetails);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task deleted");
    }


    @PatchMapping("/{id}/start")
    public ResponseEntity<String> startTask(@PathVariable long id) {
        taskService.startTask(id);
        return ResponseEntity.ok("Task started");
    }


    @PatchMapping("/{id}/complete")
    public ResponseEntity<String> completeTask(@PathVariable long id) {
        taskService.completeTask(id);
        return ResponseEntity.ok("Task completed");
    }


    @PatchMapping("/{id}/cancel")
    public ResponseEntity<String> cancelTask(@PathVariable long id) {
        taskService.cancelTask(id);
        return ResponseEntity.ok("Task cancelled");
    }
}