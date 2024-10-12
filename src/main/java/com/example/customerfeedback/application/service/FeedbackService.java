package com.example.customerfeedback.application.service;

import com.example.customerfeedback.adapter.out.persistence.FeedbackEntity;
import com.example.customerfeedback.adapter.out.persistence.FeedbackRepository;
import com.example.customerfeedback.application.port.in.SubmitFeedbackCommand;
import com.example.customerfeedback.application.port.in.SubmitFeedbackUseCase;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService implements SubmitFeedbackUseCase {
    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public void submitFeedback(SubmitFeedbackCommand command) {
        FeedbackEntity feedbackEntity = new FeedbackEntity();
        feedbackEntity.setName(command.getName());
        feedbackEntity.setEmail(command.getEmail());
        feedbackEntity.setFeedback(command.getFeedback());
        feedbackRepository.save(feedbackEntity);
    }
}
