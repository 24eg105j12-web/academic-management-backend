package com.anurag.cse.dto;

import java.time.LocalDateTime;
import java.util.List;

public class QuizResponse {
    private String topic;
    private String difficulty;
    private String format;
    private List<QuizQuestion> questions;
    private LocalDateTime generatedAt = LocalDateTime.now();
    private String providerNote;

    public QuizResponse() {}

    public QuizResponse(String topic, String difficulty, String format, List<QuizQuestion> questions,
                        LocalDateTime generatedAt, String providerNote) {
        this.topic = topic;
        this.difficulty = difficulty;
        this.format = format;
        this.questions = questions;
        this.generatedAt = generatedAt != null ? generatedAt : LocalDateTime.now();
        this.providerNote = providerNote;
    }

    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }

    public List<QuizQuestion> getQuestions() { return questions; }
    public void setQuestions(List<QuizQuestion> questions) { this.questions = questions; }

    public LocalDateTime getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; }

    public String getProviderNote() { return providerNote; }
    public void setProviderNote(String providerNote) { this.providerNote = providerNote; }

    public static QuizResponseBuilder builder() {
        return new QuizResponseBuilder();
    }

    public static class QuizResponseBuilder {
        private String topic;
        private String difficulty;
        private String format;
        private List<QuizQuestion> questions;
        private LocalDateTime generatedAt = LocalDateTime.now();
        private String providerNote;

        public QuizResponseBuilder topic(String topic) { this.topic = topic; return this; }
        public QuizResponseBuilder difficulty(String difficulty) { this.difficulty = difficulty; return this; }
        public QuizResponseBuilder format(String format) { this.format = format; return this; }
        public QuizResponseBuilder questions(List<QuizQuestion> questions) { this.questions = questions; return this; }
        public QuizResponseBuilder generatedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; return this; }
        public QuizResponseBuilder providerNote(String providerNote) { this.providerNote = providerNote; return this; }

        public QuizResponse build() {
            QuizResponse res = new QuizResponse();
            res.setTopic(this.topic);
            res.setDifficulty(this.difficulty);
            res.setFormat(this.format);
            res.setQuestions(this.questions);
            res.setGeneratedAt(this.generatedAt != null ? this.generatedAt : LocalDateTime.now());
            res.setProviderNote(this.providerNote);
            return res;
        }
    }
}
