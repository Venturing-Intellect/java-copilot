package com.example.customerfeedback.adapter.in.web;

import com.example.customerfeedback.application.port.in.SubmitFeedbackCommand;
import com.example.customerfeedback.application.port.in.SubmitFeedbackUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    private final SubmitFeedbackUseCase submitFeedbackUseCase;

    public FeedbackController(SubmitFeedbackUseCase submitFeedbackUseCase) {
        this.submitFeedbackUseCase = submitFeedbackUseCase;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> submitFeedback(@RequestBody SubmitFeedbackCommand command) {
        if (!isValidEmail(command.getEmail())) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Invalid email format");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        submitFeedbackUseCase.submitFeedback(command);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Feedback submitted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^(?!.*\\.\\.)[A-Za-z0-9+_.-]+@(?!\\.)[A-Za-z0-9.-]+(?<!\\.)\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}
