package com.dev.testquest.controller;

import com.dev.testquest.exception.JwtAuthenticationException;
import com.dev.testquest.model.Status;
import com.dev.testquest.model.Task;
import com.dev.testquest.model.dto.request.TaskDeleteRequestDto;
import com.dev.testquest.model.dto.request.TaskRequestDto;
import com.dev.testquest.model.dto.request.TaskUpdateRequestDto;
import com.dev.testquest.model.dto.response.TaskDeleteResponseDto;
import com.dev.testquest.model.dto.response.TaskResponseDto;
import com.dev.testquest.model.mapper.TaskMapper;
import com.dev.testquest.model.mapper.UserMapper;
import com.dev.testquest.security.jwt.JwtUser;
import com.dev.testquest.service.StatusService;
import com.dev.testquest.service.TaskService;
import com.dev.testquest.service.UserService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostConstruct
    public void init() {
        for (Status.StatusName statusName : Status.StatusName.values()) {
            Status status = new Status();
            status.setStatusName(statusName);
            statusService.create(status);
        }
    }

    @PostMapping("/create")
    public TaskResponseDto createTask(@RequestBody @Valid TaskRequestDto taskRequestDto,
                                      Authentication authentication) {
        Task task = taskMapper.taskRequestDtoToTask(taskRequestDto);
        taskService.create(task, authentication.getName());
        return taskMapper.taskToTaskResponseDto(task, userMapper
                .userToUserDetailsResponseDto(task.getUser()));
    }

    @PostMapping("/update")
    public TaskResponseDto update(@RequestBody @Valid TaskUpdateRequestDto taskUpdateRequestDto,
                                  Authentication authentication) {
        Task task = taskService.get(taskUpdateRequestDto.getTaskId());
        isUserHasPermission(task, authentication);
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

    @PostMapping("/delete")
    public TaskDeleteResponseDto delete(
            @RequestBody @Valid TaskDeleteRequestDto taskDeleteRequestDto,
                                        Authentication authentication) {
        Task task = taskService.get(taskDeleteRequestDto.getTaskId());
        isUserHasPermission(task, authentication);
        taskService.delete(task);
        return taskMapper.taskToTaskDeleteResponseDto(task,
                userMapper.userToUserDetailsResponseDto(task.getUser()));
    }

    @GetMapping("/via_status")
    public List<TaskResponseDto> getWithStatuses(@RequestParam @Valid @NotEmpty String status) {
        List<Task> tasks = taskService.getTasksByStatus(statusService.getByStatusName(status));
        List<TaskResponseDto> responseDtos = convertTaskToTaskDtos(tasks);
        return responseDtos;
    }

    private void isUserHasPermission(Task task, Authentication authentication) {
        if (!task.getUser().getId().equals(((JwtUser) authentication
                .getPrincipal()).getId())) {
            throw new JwtAuthenticationException("You do not have access "
                    + "to operations of this task");
        }
    }

    @GetMapping("/by_users")
    public List<TaskResponseDto> getByUsers(@RequestParam @Valid String userAge) {
        if (userAge.toLowerCase().equals("old")) {
            List<Task> tasks = taskService.getTasksByOldUsers();
            List<TaskResponseDto> taskResponseDtos = convertTaskToTaskDtos(tasks);
            return taskResponseDtos;
        }
        if (userAge.toLowerCase().equals("new")) {
            List<Task> tasks = taskService.getTasksByNewUsers();
            List<TaskResponseDto> taskResponseDtos = convertTaskToTaskDtos(tasks);
            return taskResponseDtos;
        }
        throw new IllegalArgumentException("No such validation operation!");
    }

    private List<TaskResponseDto> convertTaskToTaskDtos(List<Task> tasks) {
        List<TaskResponseDto> taskResponseDtos = new ArrayList<>();
        for (Task task : tasks) {
            taskResponseDtos.add(taskMapper.taskToTaskResponseDto(task,
                    userMapper.userToUserDetailsResponseDto(task.getUser())));
        }
        return taskResponseDtos;
    }
}
