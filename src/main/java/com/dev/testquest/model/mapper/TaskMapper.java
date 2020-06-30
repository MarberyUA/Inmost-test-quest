package com.dev.testquest.model.mapper;

import com.dev.testquest.model.Task;
import com.dev.testquest.model.dto.request.TaskRequestDto;
import com.dev.testquest.model.dto.response.TaskDeleteResponseDto;
import com.dev.testquest.model.dto.response.TaskResponseDto;
import com.dev.testquest.model.dto.response.UserDetailsResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public Task taskRequestDtoToTask(TaskRequestDto taskRequestDto) {
        Task task = new Task();
        task.setTitle(taskRequestDto.getTitle());
        task.setDescription(taskRequestDto.getDescription());
        return task;
    }

    public TaskResponseDto taskToTaskResponseDto(
            Task task, UserDetailsResponseDto userDetailsResponseDto) {
        TaskResponseDto taskResponseDto = new TaskResponseDto();
        taskResponseDto.setId(task.getId());
        taskResponseDto.setTitle(task.getTitle());
        taskResponseDto.setDescription(task.getDescription());
        taskResponseDto.setStatus(task.getStatus().getStatusName().name());
        taskResponseDto.setUserDetails(userDetailsResponseDto);
        return taskResponseDto;
    }

    public TaskDeleteResponseDto taskToTaskDeleteResponseDto(
            Task task, UserDetailsResponseDto userDetailsResponseDto) {
        TaskDeleteResponseDto taskDeleteResponseDto = new TaskDeleteResponseDto();
        taskDeleteResponseDto.setTaskId(task.getId());
        taskDeleteResponseDto.setTitle(task.getTitle());
        taskDeleteResponseDto.setDescription(task.getDescription());
        taskDeleteResponseDto.setUserDetails(userDetailsResponseDto);
        return taskDeleteResponseDto;
    }
}
