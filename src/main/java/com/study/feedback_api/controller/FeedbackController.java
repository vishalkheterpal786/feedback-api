package com.study.feedback_api.controller;

import com.study.feedback_api.dto.AddFeedbackResponse;
import com.study.feedback_api.dto.FeedbackRequest;
import com.study.feedback_api.dto.FeedbackResponse;
import com.study.feedback_api.service.FeedbackService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FeedbackController {
    private final FeedbackService feedbackService;
    private final Logger logger = LoggerFactory.getLogger(FeedbackController.class);
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    /**
     * This method add feedback
     * @param request FeedbackRequest obj
     * @return AddFeedbackResponse obj with success true or false.
     */
    @PostMapping(value = "/feedback",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AddFeedbackResponse> addFeedback(@Valid @RequestBody FeedbackRequest request) {
        logger.info("Add feedback for ContactType ", request.getContactType());
        feedbackService.addFeedback(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AddFeedbackResponse(true, "Your feedback has been successfully submitted."));
    }

    /**
     * @return List of feedback submitted
     */
    @GetMapping(value = "/feedback/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FeedbackResponse> getAllFeedback() {
        return feedbackService.getAllFeedback();
    }

    @RequestMapping("/feedback")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FeedbackResponse> getSortedFeedback(
            @RequestParam(defaultValue = "asc") String direction
    ) {
        logger.info("Returning {} feedback entries for direction ", direction);
        List<FeedbackResponse> feedbackResponseList = feedbackService.getFeedbackOrderedByDate(direction);
        logger.info("Returning {} feedback entries", feedbackResponseList.size());
        return feedbackResponseList;
    }

}
