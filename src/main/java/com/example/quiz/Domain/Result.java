package com.example.quiz.Domain;

public class Result {
    private String message;
    private boolean isCorrect;
    private boolean isLastQuestion;
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public boolean isCorrect() {
        return isCorrect;
    }
    public void setCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
    public boolean isLastQuestion() {
        return isLastQuestion;
    }
    public void setLastQuestion(boolean isLastQuestion) {
        this.isLastQuestion = isLastQuestion;
    }
    @Override
    public String toString() {
        return "Result [message=" + message + ", isCorrect=" + isCorrect + ", isLastQuestion=" + isLastQuestion
                + ", getMessage()=" + getMessage() + ", isCorrect()=" + isCorrect() + ", isLastQuestion()="
                + isLastQuestion() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
                + super.toString() + "]";
    }

    
}
