package com.study.feedback_api.dto;

import com.study.feedback_api.model.ContactType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FeedbackRequest {

    @Size(max = 100)
    private String name;

    @Size(max = 100)
    private String email;

    @NotBlank(message = "contactType is required")
    private String contactType;

    @NotBlank(message = "message is required")
    @Size(max = 1000)
    private String message;

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getContactType() { return contactType; }
    public void setContactType(String contactType) { this.contactType = contactType; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
