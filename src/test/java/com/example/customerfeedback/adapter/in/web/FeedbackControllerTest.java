package com.example.customerfeedback.adapter.in.web;

import com.example.customerfeedback.application.port.in.SubmitFeedbackCommand;
import com.example.customerfeedback.application.port.in.SubmitFeedbackUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FeedbackController.class)
public class FeedbackControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubmitFeedbackUseCase submitFeedbackUseCase;

    @Test
    void submitFeedback_validRequest_returnsOk() throws Exception {
        SubmitFeedbackCommand command = new SubmitFeedbackCommand();
        command.setEmail("test@example.com");
        command.setFeedback("Great service!");

        doNothing().when(submitFeedbackUseCase).submitFeedback(command);

        mockMvc.perform(post("/api/feedback")
                .contentType("application/json")
                .content("{\"email\":\"test@example.com\",\"feedback\":\"Great service!\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void submitFeedback_invalidEmail_returnsBadRequest() throws Exception {
        mockMvc.perform(post("/api/feedback")
                .contentType("application/json")
                .content("{\"email\":\"invalid-email\",\"feedback\":\"Great service!\"}"))
                .andExpect(status().isBadRequest());
    }
}
