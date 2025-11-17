package com.study.feedback_api.mapper;

import com.study.feedback_api.dto.FeedbackRequest;
import com.study.feedback_api.dto.FeedbackResponse;
import com.study.feedback_api.model.Feedback;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {

    default Feedback toEntity(FeedbackRequest request) {
        return new Feedback(
                request.getName(),
                request.getEmail(),
                request.getContactType(),
                request.getMessage()
        );
    }

    FeedbackResponse toResponse(Feedback feedback);

    List<FeedbackResponse> toResponseList(List<Feedback> feedbacks);
}