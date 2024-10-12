package com.example.customerfeedback.integration;

import com.example.customerfeedback.adapter.in.web.FeedbackController;
import com.example.customerfeedback.adapter.out.persistence.FeedbackEntity;
import com.example.customerfeedback.adapter.out.persistence.FeedbackRepository;
import com.example.customerfeedback.application.port.in.SubmitFeedbackCommand;
import com.example.customerfeedback.application.port.in.SubmitFeedbackUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class FeedbackControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SubmitFeedbackUseCase submitFeedbackUseCase;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Test
    void submitFeedback_validRequest_persistsToDatabase() throws Exception {
        mockMvc.perform(post("/api/feedback")
                .contentType("application/json")
                .content("{\"email\":\"test@example.com\",\"feedback\":\"Great service!\"}"))
                .andExpect(status().isOk());

        // Verify the data in the database
        FeedbackEntity feedbackEntity = feedbackRepository.findAll().get(0);
        assertThat(feedbackEntity.getEmail()).isEqualTo("test@example.com");
        assertThat(feedbackEntity.getFeedback()).isEqualTo("Great service!");
    }

    @Test
    void submitFeedback_invalidEmail_returnsBadRequest() throws Exception {
        mockMvc.perform(post("/api/feedback")
                .contentType("application/json")
                .content("{\"email\":\"invalid-email\",\"feedback\":\"Great service!\"}"))
                .andExpect(status().isBadRequest());

        // Verify that no data is persisted in the database
        assertThat(feedbackRepository.findAll()).isEmpty();
    }
}