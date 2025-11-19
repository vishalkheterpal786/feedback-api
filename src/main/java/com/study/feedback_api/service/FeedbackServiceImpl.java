package com.study.feedback_api.service;

import com.study.feedback_api.dto.FeedbackRequest;
import com.study.feedback_api.dto.FeedbackResponse;
import com.study.feedback_api.exception.FieldNotFoundException;
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

    /**
     * This method is used to store a Feedback.
     */
    public FeedbackResponse addFeedback(FeedbackRequest request) {
        if (request.message() == null
                || request.message().trim().isEmpty()
                || request.message().trim().equalsIgnoreCase("null")) {

            throw new FieldNotFoundException("message is required");
        }
        Feedback feedbackToSave = mapper.toEntity(request);
        Feedback savedFeedback = feedbackDao.save(feedbackToSave);
        return mapper.toResponse(savedFeedback);
    }

    /**
     * This method is useful to get the all the feedback.
     * @return List of Feedback
     */
    public List<FeedbackResponse> getAllFeedback() {
        List<Feedback> feedbacks = feedbackDao.findAll();
        return mapper.toResponseList(feedbacks);
    }

    /**
     * This method is used to get feedback order by date on direction passed.
     * @param direction ASC or DESC
     * @return List of ordered feedback
     */
    public List<FeedbackResponse> getFeedbackOrderedByDate(String direction) {
        List<Feedback> feedbacks = feedbackDao.findAllSorted(direction);
        return mapper.toResponseList(feedbacks);
    }

    /**
     * This method is used to Get Feedback by Contact type
     * @param type Contact Type
     * @return List of feedback
     */
    public List<FeedbackResponse> getFeedbackByContactType(ContactType type) {
        List<Feedback> feedbacks = feedbackDao.findByContactType(type);
        return mapper.toResponseList(feedbacks);
    }
}
