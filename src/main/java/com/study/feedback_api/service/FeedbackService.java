package com.study.feedback_api.service;


import com.study.feedback_api.dto.FeedbackRequest;
import com.study.feedback_api.dto.FeedbackResponse;

import java.util.List;

public interface FeedbackService {
    FeedbackResponse addFeedback(FeedbackRequest request);

    List<FeedbackResponse> getAllFeedback();

    List<FeedbackResponse> getFeedbackOrderedByDate(String direction);
}