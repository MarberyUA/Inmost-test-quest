package com.dev.testquest.controller;

import com.dev.testquest.model.Status;
import com.dev.testquest.model.Task;
import com.dev.testquest.model.dto.request.TaskRequestDto;
import com.dev.testquest.model.dto.request.TaskUpdateRequestDto;
import com.dev.testquest.model.dto.response.TaskResponseDto;
import com.dev.testquest.model.mapper.TaskMapper;
import com.dev.testquest.model.mapper.UserMapper;
import com.dev.testquest.service.StatusService;
import com.dev.testquest.service.TaskService;
import com.dev.testquest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StatusService statusService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public TaskResponseDto createTask(@RequestBody TaskRequestDto taskRequestDto,
                                      Authentication authentication) {
        Task task = taskMapper.taskRequestDtoToTask(taskRequestDto);
        taskService.create(task, authentication.getName());
        return taskMapper.taskToTaskResponseDto(task, userMapper
                .userToUserDetailsResponseDto(task.getUser()));
    }

    @PostMapping("/update")
    public TaskResponseDto update(@RequestBody TaskUpdateRequestDto taskUpdateRequestDto) {
        Task task = taskService.get(taskUpdateRequestDto.getTaskId());
        if (taskUpdateRequestDto.getStatusName() != null) {
            Status status = statusService.getByStatusName(taskUpdateRequestDto
                    .getStatusName());
            task.setStatus(status);
        }
        if (taskUpdateRequestDto.getDescription() != null) {
            task.setDescription(taskUpdateRequestDto.getDescription());
        }
        if (taskUpdateRequestDto.getUserEmail() != null) {
            task.setUser(userService.findByEmail(taskUpdateRequestDto.getUserEmail()));
        }
        taskService.update(task);
        return taskMapper.taskToTaskResponseDto(task, userMapper
                .userToUserDetailsResponseDto(task.getUser()));
    }
}
