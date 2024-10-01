package com.sgdcbrk.crm.business.abstracts;

import com.sgdcbrk.crm.model.task.Task;

import java.util.List;

public interface TaskService {
    Task getTask(long id);
    List<Task> getTasks();

    void addTask(Task task);
    void deleteTask(long id);
    void updateTask(long id,Task task);

    void startTask(long id);
    void completeTask(long id);
    void cancelTask(long id);
}
