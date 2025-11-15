package com.study.feedback_api.dto;

import com.study.feedback_api.model.ContactType;

import java.time.OffsetDateTime;

public record FeedbackResponse(
        Long id,
        OffsetDateTime createdAt,
        String name,
        String email,
        ContactType contactType,
        String message
) {}