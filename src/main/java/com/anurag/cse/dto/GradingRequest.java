package com.anurag.cse.dto;

import jakarta.validation.constraints.NotBlank;

public class GradingRequest {
    @NotBlank(message = "Assignment title is required")
    private String assignmentTitle;

    @NotBlank(message = "Rubric is required")
    private String rubric;

    private Integer maxScore = 100;

    @NotBlank(message = "Student submission content is required")
    private String studentSubmission;

    private Long submissionId;

    public GradingRequest() {}

    public GradingRequest(String assignmentTitle, String rubric, Integer maxScore,
                          String studentSubmission, Long submissionId) {
        this.assignmentTitle = assignmentTitle;
        this.rubric = rubric;
        this.maxScore = maxScore != null ? maxScore : 100;
        this.studentSubmission = studentSubmission;
        this.submissionId = submissionId;
    }

    public String getAssignmentTitle() { return assignmentTitle; }
    public void setAssignmentTitle(String assignmentTitle) { this.assignmentTitle = assignmentTitle; }

    public String getRubric() { return rubric; }
    public void setRubric(String rubric) { this.rubric = rubric; }

    public Integer getMaxScore() { return maxScore; }
    public void setMaxScore(Integer maxScore) { this.maxScore = maxScore; }

    public String getStudentSubmission() { return studentSubmission; }
    public void setStudentSubmission(String studentSubmission) { this.studentSubmission = studentSubmission; }

    public Long getSubmissionId() { return submissionId; }
    public void setSubmissionId(Long submissionId) { this.submissionId = submissionId; }
}
