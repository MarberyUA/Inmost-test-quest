package com.dev.testquest.model.dto.response;

public class TaskDeleteResponseDto {
    private Long taskId;
    private String description;
    private UserDetailsResponseDto userDetails;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserDetailsResponseDto getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetailsResponseDto userDetails) {
        this.userDetails = userDetails;
    }
}
