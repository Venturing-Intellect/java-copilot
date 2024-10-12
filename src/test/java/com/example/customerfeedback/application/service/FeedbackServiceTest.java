package com.example.customerfeedback.application.service;

import com.example.customerfeedback.adapter.out.persistence.FeedbackEntity;
import com.example.customerfeedback.adapter.out.persistence.FeedbackRepository;
import com.example.customerfeedback.application.port.in.SubmitFeedbackCommand;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class FeedbackServiceTest {
    @Test
    void submitFeedback_savesFeedback() {
        FeedbackRepository feedbackRepository = Mockito.mock(FeedbackRepository.class);
        FeedbackService feedbackService = new FeedbackService(feedbackRepository);

        SubmitFeedbackCommand command = new SubmitFeedbackCommand();
        command.setEmail("test@example.com");
        command.setFeedback("Great service!");

        feedbackService.submitFeedback(command);

        verify(feedbackRepository).save(any(FeedbackEntity.class));
    }
}
