package com.example.customerfeedback.adapter.in.web;

import com.example.customerfeedback.application.port.in.SubmitFeedbackCommand;
import com.example.customerfeedback.application.port.in.SubmitFeedbackUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    private final SubmitFeedbackUseCase submitFeedbackUseCase;

    public FeedbackController(SubmitFeedbackUseCase submitFeedbackUseCase) {
        this.submitFeedbackUseCase = submitFeedbackUseCase;
    }

    @PostMapping
    public ResponseEntity<String> submitFeedback(@RequestBody SubmitFeedbackCommand command) {
        if (!isValidEmail(command.getEmail())) {
            return new ResponseEntity<>("Invalid email format", HttpStatus.BAD_REQUEST);
        }
        submitFeedbackUseCase.submitFeedback(command);
        return new ResponseEntity<>("Feedback submitted successfully", HttpStatus.OK);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^(?!.*\\.\\.)[A-Za-z0-9+_.-]+@(?!\\.)[A-Za-z0-9.-]+(?<!\\.)\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}
