package com.study.feedback_api.controller;

import com.study.feedback_api.dto.AddFeedbackResponse;
import com.study.feedback_api.dto.FeedbackRequest;
import com.study.feedback_api.dto.FeedbackResponse;
import com.study.feedback_api.model.ContactType;
import com.study.feedback_api.service.FeedbackService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;
    private final Logger logger = LoggerFactory.getLogger(FeedbackController.class);

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    /**
     * This method save feedback
     *
     * @param request FeedbackRequest obj
     * @return AddFeedbackResponse obj with success message.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddFeedbackResponse> addFeedback(@Valid @RequestBody FeedbackRequest request) {
        logger.info("Add feedback for ContactType ", request.contactType());
        feedbackService.addFeedback(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AddFeedbackResponse(true, "Your feedback has been successfully submitted."));
    }

    /**
     * This method returns list of feedbacks.
     *
     * @return List of feedback submitted
     */
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FeedbackResponse>> getAllFeedback() {
        List<FeedbackResponse> feedbackResponseList = feedbackService.getAllFeedback();
        return ResponseEntity.ok(feedbackResponseList);
    }

    /**
     * This method returns list of sorted feedback
     *
     * @param direction
     * @return list of sorted feedback list on the basis of direction.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FeedbackResponse>> getSortedFeedback(@RequestParam(defaultValue = "asc") String direction) {
        logger.info("Returning {} feedback entries for direction ", direction);
        List<FeedbackResponse> feedbackResponseList = feedbackService.getFeedbackOrderedByDate(direction);
        logger.info("Returning {} feedback entries", feedbackResponseList.size());
        return ResponseEntity.ok(feedbackResponseList);
    }

    /**
     * Get feedback filtered by contact type
     *
     * @param type ContactType (mandatory)
     */
    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FeedbackResponse>> getFeedbackByContactType(@RequestParam @NotNull String type) {
        ContactType contactType = ContactType.fromValue(type);
        logger.info("Fetching feedback for contactType: {}", contactType);
        List<FeedbackResponse> feedbacks = feedbackService.getFeedbackByContactType(contactType);
        logger.info("Returning {} feedback entries", feedbacks.size());
        return ResponseEntity.ok(feedbacks);
    }

}
