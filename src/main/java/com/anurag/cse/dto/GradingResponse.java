package com.anurag.cse.dto;

import java.util.List;

public class GradingResponse {
    private Integer suggestedScore;
    private Integer maxScore;
    private Double percentage;
    private String gradeLetter;
    private String summaryFeedback;
    private List<String> strengths;
    private List<String> areasForImprovement;
    private List<RubricItemEvaluation> rubricBreakdown;
    private String evaluationSource;

    public GradingResponse() {}

    public GradingResponse(Integer suggestedScore, Integer maxScore, Double percentage,
                           String gradeLetter, String summaryFeedback, List<String> strengths,
                           List<String> areasForImprovement,
                           List<RubricItemEvaluation> rubricBreakdown, String evaluationSource) {
        this.suggestedScore = suggestedScore;
        this.maxScore = maxScore;
        this.percentage = percentage;
        this.gradeLetter = gradeLetter;
        this.summaryFeedback = summaryFeedback;
        this.strengths = strengths;
        this.areasForImprovement = areasForImprovement;
        this.rubricBreakdown = rubricBreakdown;
        this.evaluationSource = evaluationSource;
    }

    public Integer getSuggestedScore() { return suggestedScore; }
    public void setSuggestedScore(Integer suggestedScore) { this.suggestedScore = suggestedScore; }

    public Integer getMaxScore() { return maxScore; }
    public void setMaxScore(Integer maxScore) { this.maxScore = maxScore; }

    public Double getPercentage() { return percentage; }
    public void setPercentage(Double percentage) { this.percentage = percentage; }

    public String getGradeLetter() { return gradeLetter; }
    public void setGradeLetter(String gradeLetter) { this.gradeLetter = gradeLetter; }

    public String getSummaryFeedback() { return summaryFeedback; }
    public void setSummaryFeedback(String summaryFeedback) { this.summaryFeedback = summaryFeedback; }

    public List<String> getStrengths() { return strengths; }
    public void setStrengths(List<String> strengths) { this.strengths = strengths; }

    public List<String> getAreasForImprovement() { return areasForImprovement; }
    public void setAreasForImprovement(List<String> areasForImprovement) { this.areasForImprovement = areasForImprovement; }

    public List<RubricItemEvaluation> getRubricBreakdown() { return rubricBreakdown; }
    public void setRubricBreakdown(List<RubricItemEvaluation> rubricBreakdown) { this.rubricBreakdown = rubricBreakdown; }

    public String getEvaluationSource() { return evaluationSource; }
    public void setEvaluationSource(String evaluationSource) { this.evaluationSource = evaluationSource; }

    public static GradingResponseBuilder builder() {
        return new GradingResponseBuilder();
    }

    public static class GradingResponseBuilder {
        private Integer suggestedScore;
        private Integer maxScore;
        private Double percentage;
        private String gradeLetter;
        private String summaryFeedback;
        private List<String> strengths;
        private List<String> areasForImprovement;
        private List<RubricItemEvaluation> rubricBreakdown;
        private String evaluationSource;

        public GradingResponseBuilder suggestedScore(Integer suggestedScore) { this.suggestedScore = suggestedScore; return this; }
        public GradingResponseBuilder maxScore(Integer maxScore) { this.maxScore = maxScore; return this; }
        public GradingResponseBuilder percentage(Double percentage) { this.percentage = percentage; return this; }
        public GradingResponseBuilder gradeLetter(String gradeLetter) { this.gradeLetter = gradeLetter; return this; }
        public GradingResponseBuilder summaryFeedback(String summaryFeedback) { this.summaryFeedback = summaryFeedback; return this; }
        public GradingResponseBuilder strengths(List<String> strengths) { this.strengths = strengths; return this; }
        public GradingResponseBuilder areasForImprovement(List<String> areasForImprovement) { this.areasForImprovement = areasForImprovement; return this; }
        public GradingResponseBuilder rubricBreakdown(List<RubricItemEvaluation> rubricBreakdown) { this.rubricBreakdown = rubricBreakdown; return this; }
        public GradingResponseBuilder evaluationSource(String evaluationSource) { this.evaluationSource = evaluationSource; return this; }

        public GradingResponse build() {
            GradingResponse res = new GradingResponse();
            res.setSuggestedScore(this.suggestedScore);
            res.setMaxScore(this.maxScore);
            res.setPercentage(this.percentage);
            res.setGradeLetter(this.gradeLetter);
            res.setSummaryFeedback(this.summaryFeedback);
            res.setStrengths(this.strengths);
            res.setAreasForImprovement(this.areasForImprovement);
            res.setRubricBreakdown(this.rubricBreakdown);
            res.setEvaluationSource(this.evaluationSource);
            return res;
        }
    }

    public static class RubricItemEvaluation {
        private String criterion;
        private Integer pointsAwarded;
        private Integer pointsPossible;
        private String comments;

        public RubricItemEvaluation() {}

        public RubricItemEvaluation(String criterion, Integer pointsAwarded, Integer pointsPossible, String comments) {
            this.criterion = criterion;
            this.pointsAwarded = pointsAwarded;
            this.pointsPossible = pointsPossible;
            this.comments = comments;
        }

        public String getCriterion() { return criterion; }
        public void setCriterion(String criterion) { this.criterion = criterion; }

        public Integer getPointsAwarded() { return pointsAwarded; }
        public void setPointsAwarded(Integer pointsAwarded) { this.pointsAwarded = pointsAwarded; }

        public Integer getPointsPossible() { return pointsPossible; }
        public void setPointsPossible(Integer pointsPossible) { this.pointsPossible = pointsPossible; }

        public String getComments() { return comments; }
        public void setComments(String comments) { this.comments = comments; }

        public static RubricItemEvaluationBuilder builder() {
            return new RubricItemEvaluationBuilder();
        }

        public static class RubricItemEvaluationBuilder {
            private String criterion;
            private Integer pointsAwarded;
            private Integer pointsPossible;
            private String comments;

            public RubricItemEvaluationBuilder criterion(String criterion) { this.criterion = criterion; return this; }
            public RubricItemEvaluationBuilder pointsAwarded(Integer pointsAwarded) { this.pointsAwarded = pointsAwarded; return this; }
            public RubricItemEvaluationBuilder pointsPossible(Integer pointsPossible) { this.pointsPossible = pointsPossible; return this; }
            public RubricItemEvaluationBuilder comments(String comments) { this.comments = comments; return this; }

            public RubricItemEvaluation build() {
                RubricItemEvaluation item = new RubricItemEvaluation();
                item.setCriterion(this.criterion);
                item.setPointsAwarded(this.pointsAwarded);
                item.setPointsPossible(this.pointsPossible);
                item.setComments(this.comments);
                return item;
            }
        }
    }
}
