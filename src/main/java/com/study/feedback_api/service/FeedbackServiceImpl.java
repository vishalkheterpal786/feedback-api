package com.study.feedback_api.service;

import com.study.feedback_api.dto.FeedbackRequest;
import com.study.feedback_api.model.ContactType;
import com.study.feedback_api.model.Feedback;
import com.study.feedback_api.repository.FeedbackDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackDao feedbackDao;

    public FeedbackServiceImpl(FeedbackDao feedbackDao) {
        this.feedbackDao = feedbackDao;
    }

    @Override
    public Feedback addFeedback(FeedbackRequest request) {
        Feedback feedback = new Feedback(
                request.getName(),
                request.getEmail(),
                ContactType.valueOf(request.getContactType().trim().toUpperCase()),
                request.getMessage());

        return feedbackDao.save(feedback);
    }

    @Override
    public List<Feedback> getAllFeedback() {
        return feedbackDao.findAll();
    }
}
