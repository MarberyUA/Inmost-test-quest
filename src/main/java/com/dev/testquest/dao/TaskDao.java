package com.dev.testquest.dao;

import com.dev.testquest.model.Task;
import java.util.List;

public interface TaskDao {
    Task create(Task task);

    Task update(Task task);

    void delete(Task task);

    List<Task> getAll();
}
