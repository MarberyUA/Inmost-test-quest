package com.dev.testquest.model.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TaskRequestDto {
    @NotNull
    @NotEmpty
    private String title;
    @NotNull
    @NotEmpty
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
