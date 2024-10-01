package com.sgdcbrk.crm.business.concretes;

import com.sgdcbrk.crm.business.abstracts.TaskService;
import com.sgdcbrk.crm.model.task.Task;
import com.sgdcbrk.crm.model.task.TaskStatus;
import com.sgdcbrk.crm.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskManager implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public Task getTask(long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void addTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void updateTask(long id, Task taskDetails) {
        Task task = getTask(id);
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setUser(taskDetails.getUser());
        task.setCustomer(taskDetails.getCustomer());
        task.setOpportunity(taskDetails.getOpportunity());
        task.setType(taskDetails.getType());
        task.setStatus(taskDetails.getStatus());
        task.setDateOfMeeting(taskDetails.getDateOfMeeting());
        taskRepository.save(task);
    }

    @Override
    public void startTask(long id) {
        Task task = getTask(id);
        task.setStatus(TaskStatus.IN_PROGRESS);
        taskRepository.save(task);
    }

    @Override
    public void completeTask(long id) {
        Task task = getTask(id);
        task.setStatus(TaskStatus.COMPLETED);
        taskRepository.save(task);
    }

    @Override
    public void cancelTask(long id) {
        Task task = getTask(id);
        task.setStatus(TaskStatus.CANCELLED);
        taskRepository.save(task);
    }

}