package com.study.feedback_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.feedback_api.dto.FeedbackRequest;
import com.study.feedback_api.model.ContactType;
import com.study.feedback_api.model.Feedback;
import com.study.feedback_api.repository.FeedbackDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class FeedbackControllerTest {


    private MockMvc mockMvc;

    private FeedbackDao feedbackDao;

    private ObjectMapper objectMapper;

    @Autowired
    FeedbackControllerTest(MockMvc mockMvc, FeedbackDao feedbackDao, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.feedbackDao = feedbackDao;
        this.objectMapper = objectMapper;
    }

    @Test
    void shouldCreateFeedbackAndPersist() throws Exception {
        FeedbackRequest request = new FeedbackRequest();
        request.setName("Test");
        request.setEmail("Test@test.com");
        request.setContactType(ContactType.SUPPORT.name());
        request.setMessage("Integration test message");

        mockMvc.perform(post("/api/feedback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Your feedback has been successfully submitted."));


        List<Feedback> allFeedback = feedbackDao.findAll();
        assertThat(allFeedback).hasSize(1);
        Feedback saved = allFeedback.get(0);
        assertThat(saved.getName()).isEqualTo("Test");
        assertThat(saved.getEmail()).isEqualTo("Test@test.com");
        assertThat(saved.getContactType()).isEqualTo(ContactType.SUPPORT);
        assertThat(saved.getMessage()).isEqualTo("Integration test message");
    }

    @Test
    void shouldReturnAllFeedback() throws Exception {
        Feedback f1 = new Feedback("Alice", "alice@example.com", ContactType.GENERAL, "Message 1");
        Feedback f2 = new Feedback("Bob", "bob@example.com", ContactType.MARKETING, "Message 2");
        feedbackDao.save(f1);
        feedbackDao.save(f2);

        mockMvc.perform(get("/api/feedback")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[1].contactType").value("marketing"));
    }

    @Test
    void shouldReturnBadRequestForBlankMessage() throws Exception {
        FeedbackRequest invalidRequest = new FeedbackRequest();
        invalidRequest.setMessage("");
        invalidRequest.setContactType(ContactType.SUPPORT.name());

        mockMvc.perform(post("/api/feedback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
    }
    @Test
    void shouldReturnBadRequestForInvalidContactType() throws Exception {
        FeedbackRequest invalidRequest = new FeedbackRequest();
        invalidRequest.setMessage("Here is a message");
        invalidRequest.setContactType("");

        mockMvc.perform(post("/api/feedback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
    }
}