package com.dev.testquest.service;

import com.dev.testquest.model.Status;
import com.dev.testquest.model.Task;
import java.util.List;

public interface TaskService {
    Task create(Task task, String userEmail);

    Task update(Task task);

    void delete(Task task);

    List<Task> getAll();

    List<Task> getTasksByStatus(Status status);

    List<Task> getByUserId(Long id);

    Task get(Long id);

    List<Task> getTasksByOldUsers();

    List<Task> getTasksByNewUsers();

}
