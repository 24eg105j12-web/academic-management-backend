package com.anurag.cse.dto;

import java.util.List;

public class QuizQuestion {
    private Integer id;
    private String question;
    private List<String> options;
    private String correctAnswer;
    private String explanation;
    private String flashcardFront;
    private String flashcardBack;

    public QuizQuestion() {}

    public QuizQuestion(Integer id, String question, List<String> options, String correctAnswer,
                        String explanation, String flashcardFront, String flashcardBack) {
        this.id = id;
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.explanation = explanation;
        this.flashcardFront = flashcardFront;
        this.flashcardBack = flashcardBack;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public List<String> getOptions() { return options; }
    public void setOptions(List<String> options) { this.options = options; }

    public String getCorrectAnswer() { return correctAnswer; }
    public void setCorrectAnswer(String correctAnswer) { this.correctAnswer = correctAnswer; }

    public String getExplanation() { return explanation; }
    public void setExplanation(String explanation) { this.explanation = explanation; }

    public String getFlashcardFront() { return flashcardFront; }
    public void setFlashcardFront(String flashcardFront) { this.flashcardFront = flashcardFront; }

    public String getFlashcardBack() { return flashcardBack; }
    public void setFlashcardBack(String flashcardBack) { this.flashcardBack = flashcardBack; }

    public static QuizQuestionBuilder builder() {
        return new QuizQuestionBuilder();
    }

    public static class QuizQuestionBuilder {
        private Integer id;
        private String question;
        private List<String> options;
        private String correctAnswer;
        private String explanation;
        private String flashcardFront;
        private String flashcardBack;

        public QuizQuestionBuilder id(Integer id) { this.id = id; return this; }
        public QuizQuestionBuilder question(String question) { this.question = question; return this; }
        public QuizQuestionBuilder options(List<String> options) { this.options = options; return this; }
        public QuizQuestionBuilder correctAnswer(String correctAnswer) { this.correctAnswer = correctAnswer; return this; }
        public QuizQuestionBuilder explanation(String explanation) { this.explanation = explanation; return this; }
        public QuizQuestionBuilder flashcardFront(String flashcardFront) { this.flashcardFront = flashcardFront; return this; }
        public QuizQuestionBuilder flashcardBack(String flashcardBack) { this.flashcardBack = flashcardBack; return this; }

        public QuizQuestion build() {
            QuizQuestion q = new QuizQuestion();
            q.setId(this.id);
            q.setQuestion(this.question);
            q.setOptions(this.options);
            q.setCorrectAnswer(this.correctAnswer);
            q.setExplanation(this.explanation);
            q.setFlashcardFront(this.flashcardFront);
            q.setFlashcardBack(this.flashcardBack);
            return q;
        }
    }
}
