package com.study.feedback_api.service;

import com.study.feedback_api.dto.FeedbackRequest;
import com.study.feedback_api.dto.FeedbackResponse;
import com.study.feedback_api.mapper.FeedbackMapper;
import com.study.feedback_api.model.ContactType;
import com.study.feedback_api.model.Feedback;
import com.study.feedback_api.repository.FeedbackDao;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackDao feedbackDao;
    private final FeedbackMapper mapper;

    public FeedbackServiceImpl(FeedbackDao feedbackDao, FeedbackMapper mapper) {
        this.feedbackDao = feedbackDao;
        this.mapper = mapper;
    }


    public FeedbackResponse addFeedback(FeedbackRequest request) {
        Feedback feedbackToSave = mapper.toEntity(request);
        Feedback savedFeedback = feedbackDao.save(feedbackToSave);
        return mapper.toResponse(savedFeedback);
    }


    public List<FeedbackResponse> getAllFeedback() {
        List<Feedback> feedbacks = feedbackDao.findAll();
        return mapper.toResponseList(feedbacks);
    }


    public List<FeedbackResponse> getFeedbackOrderedByDate(String direction) {
        List<Feedback> feedbacks = feedbackDao.findAllSorted(direction);
        return mapper.toResponseList(feedbacks);
    }

    public List<FeedbackResponse> getFeedbackByContactType(ContactType type) {
        List<Feedback> feedbacks = feedbackDao.findByContactType(type);
        return mapper.toResponseList(feedbacks);
    }
}
