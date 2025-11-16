package com.study.feedback_api.service;

import com.study.feedback_api.dto.FeedbackRequest;
import com.study.feedback_api.model.ContactType;
import com.study.feedback_api.model.Feedback;
import com.study.feedback_api.repository.FeedbackDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackDao feedbackDao;

    public FeedbackService(FeedbackDao feedbackDao) {
        this.feedbackDao = feedbackDao;
    }

    public Feedback addFeedback(FeedbackRequest request) {
        Feedback feedback = new Feedback(
                request.getName(),
                request.getEmail(),
                ContactType.valueOf(request.getContactType().trim()),
                request.getMessage());

        return feedbackDao.save(feedback);
    }

    public List<Feedback> getAllFeedback() {
        return feedbackDao.findAll();
    }
}
