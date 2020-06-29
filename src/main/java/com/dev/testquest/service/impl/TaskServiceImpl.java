package com.dev.testquest.service.impl;

import com.dev.testquest.dao.TaskDao;
import com.dev.testquest.model.Status;
import com.dev.testquest.model.Task;
import com.dev.testquest.model.User;
import com.dev.testquest.service.StatusService;
import com.dev.testquest.service.TaskService;
import com.dev.testquest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        user.setTasks(task);
        userService.update(user);
        return task;
    }

    @Override
    public Task update(Task task) {
        return null;
    }

    @Override
    public void delete(Task task) {

    }

    @Override
    public List<Task> getAll() {
        return null;
    }
}
