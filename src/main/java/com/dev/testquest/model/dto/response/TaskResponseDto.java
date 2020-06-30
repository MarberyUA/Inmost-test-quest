package com.dev.testquest.model.dto.response;

public class TaskResponseDto {
    private Long id;
    private String description;
    private UserDetailsResponseDto userDetails;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
