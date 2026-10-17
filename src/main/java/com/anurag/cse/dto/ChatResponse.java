package com.anurag.cse.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ChatResponse {
    private String reply;
    private List<String> citedPolicies;
    private List<String> suggestedQuestions;
    private LocalDateTime timestamp = LocalDateTime.now();
    private String confidence;

    public ChatResponse() {}

    public ChatResponse(String reply, List<String> citedPolicies, List<String> suggestedQuestions,
                        LocalDateTime timestamp, String confidence) {
        this.reply = reply;
        this.citedPolicies = citedPolicies;
        this.suggestedQuestions = suggestedQuestions;
        this.timestamp = timestamp != null ? timestamp : LocalDateTime.now();
        this.confidence = confidence;
    }

    public String getReply() { return reply; }
    public void setReply(String reply) { this.reply = reply; }

    public List<String> getCitedPolicies() { return citedPolicies; }
    public void setCitedPolicies(List<String> citedPolicies) { this.citedPolicies = citedPolicies; }

    public List<String> getSuggestedQuestions() { return suggestedQuestions; }
    public void setSuggestedQuestions(List<String> suggestedQuestions) { this.suggestedQuestions = suggestedQuestions; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getConfidence() { return confidence; }
    public void setConfidence(String confidence) { this.confidence = confidence; }

    public static ChatResponseBuilder builder() {
        return new ChatResponseBuilder();
    }

    public static class ChatResponseBuilder {
        private String reply;
        private List<String> citedPolicies;
        private List<String> suggestedQuestions;
        private LocalDateTime timestamp = LocalDateTime.now();
        private String confidence;

        public ChatResponseBuilder reply(String reply) { this.reply = reply; return this; }
        public ChatResponseBuilder citedPolicies(List<String> citedPolicies) { this.citedPolicies = citedPolicies; return this; }
        public ChatResponseBuilder suggestedQuestions(List<String> suggestedQuestions) { this.suggestedQuestions = suggestedQuestions; return this; }
        public ChatResponseBuilder timestamp(LocalDateTime timestamp) { this.timestamp = timestamp; return this; }
        public ChatResponseBuilder confidence(String confidence) { this.confidence = confidence; return this; }

        public ChatResponse build() {
            ChatResponse res = new ChatResponse();
            res.setReply(this.reply);
            res.setCitedPolicies(this.citedPolicies);
            res.setSuggestedQuestions(this.suggestedQuestions);
            res.setTimestamp(this.timestamp != null ? this.timestamp : LocalDateTime.now());
            res.setConfidence(this.confidence);
            return res;
        }
    }
}
