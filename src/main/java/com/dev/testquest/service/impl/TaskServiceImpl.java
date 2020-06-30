package com.dev.testquest.service.impl;

import com.dev.testquest.dao.TaskDao;
import com.dev.testquest.model.Status;
import com.dev.testquest.model.Task;
import com.dev.testquest.model.User;
import com.dev.testquest.service.StatusService;
import com.dev.testquest.service.TaskService;
import com.dev.testquest.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskDao taskDao;

    @Autowired
    private UserService userService;

    @Autowired
    private StatusService statusService;

    @Override
    public Task create(Task task, String userEmail) {
        task.setStatus(statusService.getByStatusName("In_Progress"));
        User user = userService.findByEmail(userEmail);
        task.setUser(user);
        task = taskDao.create(task);
        return task;
    }

    @Override
    public Task update(Task task) {
        return taskDao.update(task);
    }

    @Override
    public void delete(Task task) {
        taskDao.delete(task);
    }

    @Override
    public List<Task> getAll() {
        return taskDao.getAll();
    }

    @Override
    public List<Task> getTasksByStatus(Status status) {
        return taskDao.getTasksByStatus(status);
    }

    @Override
    public List<Task> getByUserId(Long id) {
        return taskDao.getByUserId(id);
    }

    @Override
    public Task get(Long id) {
        return taskDao.get(id);
    }

    @Override
    public List<Task> getTasksByOldUsers() {
        return taskDao.getTasksByOldUsers();
    }

    @Override
    public List<Task> getTasksByNewUsers() {
        return taskDao.getTasksByNewUsers();
    }
}
