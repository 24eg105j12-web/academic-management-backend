package com.anurag.cse.dto;

import jakarta.validation.constraints.NotBlank;

public class QuizRequest {
    @NotBlank(message = "Topic or syllabus text is required")
    private String topic;

    private Integer numberOfQuestions = 5;

    private String difficulty = "MEDIUM"; // EASY, MEDIUM, HARD

    private String format = "MCQ"; // MCQ, FLASHCARD

    public QuizRequest() {}

    public QuizRequest(String topic, Integer numberOfQuestions, String difficulty, String format) {
        this.topic = topic;
        this.numberOfQuestions = numberOfQuestions != null ? numberOfQuestions : 5;
        this.difficulty = difficulty != null ? difficulty : "MEDIUM";
        this.format = format != null ? format : "MCQ";
    }

    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }

    public Integer getNumberOfQuestions() { return numberOfQuestions; }
    public void setNumberOfQuestions(Integer numberOfQuestions) { this.numberOfQuestions = numberOfQuestions; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }
}
