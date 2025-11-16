package com.study.feedback_api.service;


import com.study.feedback_api.dto.FeedbackRequest;
import com.study.feedback_api.model.Feedback;

import java.util.List;

public interface FeedbackService {
    Feedback addFeedback(FeedbackRequest request);
    List<Feedback> getAllFeedback();
}