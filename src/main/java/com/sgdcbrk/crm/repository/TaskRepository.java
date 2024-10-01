package com.sgdcbrk.crm.repository;

import com.sgdcbrk.crm.model.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
