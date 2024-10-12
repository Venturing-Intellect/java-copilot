package com.example.customerfeedback.application.port.in;

public class SubmitFeedbackCommand {
    private String email;
    private String feedback;

    // Getters
    public String getEmail() {
        return email;
    }

    public String getFeedback() {
        return feedback;
    }

    // Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
