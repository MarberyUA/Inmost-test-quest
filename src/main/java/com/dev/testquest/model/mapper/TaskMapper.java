package com.dev.testquest.model.mapper;

import com.dev.testquest.model.Task;
import com.dev.testquest.model.dto.request.TaskRequestDto;
import com.dev.testquest.model.dto.response.TaskResponseDto;
import com.dev.testquest.model.dto.response.UserDetailsResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public Task taskRequestDtoToTask(TaskRequestDto taskRequestDro) {
        Task task = new Task();
        return task;
    }

    public TaskResponseDto taskToTaskResponseDto(Task task, UserDetailsResponseDto
            userDetailsResponseDto) {
        TaskResponseDto taskResponseDto = new TaskResponseDto();
        taskResponseDto.setId(task.getId());
        taskResponseDto.setDescription(task.getDescription());
        taskResponseDto.setStatus(task.getStatus().getStatusName().name());
        taskResponseDto.setUserDetails(userDetailsResponseDto);
        return taskResponseDto;
    }
}
