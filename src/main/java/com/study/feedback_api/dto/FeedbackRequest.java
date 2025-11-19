package com.study.feedback_api.dto;

import com.study.feedback_api.model.ContactType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FeedbackRequest(@Size(max = 100) String name,
                              @Size(max = 100) String email,
                              @NotNull(message = "contactType is required") ContactType contactType,
                              @NotBlank(message = "message is required") @Size(max = 1000) String message) {

}
