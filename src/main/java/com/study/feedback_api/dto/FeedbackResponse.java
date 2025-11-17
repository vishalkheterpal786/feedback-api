package com.study.feedback_api.dto;

import com.study.feedback_api.model.ContactType;

import java.time.LocalDateTime;

public record FeedbackResponse(
        Long id,
        LocalDateTime createdAt,
        String name,
        String email,
        ContactType contactType,
        String message
) {}