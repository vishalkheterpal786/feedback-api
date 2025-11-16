package com.study.feedback_api.controller;

import com.study.feedback_api.dto.AddFeedbackResponse;
import com.study.feedback_api.dto.FeedbackRequest;
import com.study.feedback_api.model.Feedback;
import com.study.feedback_api.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    /**
     * This method add feedback
     * @param request FeedbackRequest obj
     * @return AddFeedbackResponse obj with success true or false.
     */
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AddFeedbackResponse> addFeedback(@Valid @RequestBody FeedbackRequest request) {
        feedbackService.addFeedback(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AddFeedbackResponse(true, "Your feedback has been successfully submitted."));
    }

    /**
     *
     * @return List of feedback submitted
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Feedback> getAllFeedback() {
        return feedbackService.getAllFeedback();
    }

}
