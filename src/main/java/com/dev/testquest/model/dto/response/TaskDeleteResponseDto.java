package com.dev.testquest.model.dto.response;

public class TaskDeleteResponseDto {
    private Long taskId;
    private String title;
    private String description;
    private UserDetailsResponseDto userDetails;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
