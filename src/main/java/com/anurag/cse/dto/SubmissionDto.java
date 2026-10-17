package com.anurag.cse.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class SubmissionDto {
    private Long id;

    @NotNull(message = "Assignment ID is required")
    private Long assignmentId;
    private String assignmentTitle;
    private String assignmentRubric;
    private Integer assignmentMaxScore;
    private String courseCode;
    private String courseTitle;

    private Long studentId;
    private String studentName;
    private String studentCode;
    private String studentEmail;

    @NotBlank(message = "Submission content is required")
    private String content;

    private LocalDateTime submissionDate;
    private Integer score;
    private String aiFeedback;
    private String facultyNotes;
    private String status;

    public SubmissionDto() {}

    public SubmissionDto(Long id, Long assignmentId, String assignmentTitle, String assignmentRubric,
                         Integer assignmentMaxScore, String courseCode, String courseTitle,
                         Long studentId, String studentName, String studentCode, String studentEmail,
                         String content, LocalDateTime submissionDate, Integer score,
                         String aiFeedback, String facultyNotes, String status) {
        this.id = id;
        this.assignmentId = assignmentId;
        this.assignmentTitle = assignmentTitle;
        this.assignmentRubric = assignmentRubric;
        this.assignmentMaxScore = assignmentMaxScore;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentCode = studentCode;
        this.studentEmail = studentEmail;
        this.content = content;
        this.submissionDate = submissionDate;
        this.score = score;
        this.aiFeedback = aiFeedback;
        this.facultyNotes = facultyNotes;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getAssignmentId() { return assignmentId; }
    public void setAssignmentId(Long assignmentId) { this.assignmentId = assignmentId; }

    public String getAssignmentTitle() { return assignmentTitle; }
    public void setAssignmentTitle(String assignmentTitle) { this.assignmentTitle = assignmentTitle; }

    public String getAssignmentRubric() { return assignmentRubric; }
    public void setAssignmentRubric(String assignmentRubric) { this.assignmentRubric = assignmentRubric; }

    public Integer getAssignmentMaxScore() { return assignmentMaxScore; }
    public void setAssignmentMaxScore(Integer assignmentMaxScore) { this.assignmentMaxScore = assignmentMaxScore; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public String getCourseTitle() { return courseTitle; }
    public void setCourseTitle(String courseTitle) { this.courseTitle = courseTitle; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getStudentCode() { return studentCode; }
    public void setStudentCode(String studentCode) { this.studentCode = studentCode; }

    public String getStudentEmail() { return studentEmail; }
    public void setStudentEmail(String studentEmail) { this.studentEmail = studentEmail; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getSubmissionDate() { return submissionDate; }
    public void setSubmissionDate(LocalDateTime submissionDate) { this.submissionDate = submissionDate; }

    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }

    public String getAiFeedback() { return aiFeedback; }
    public void setAiFeedback(String aiFeedback) { this.aiFeedback = aiFeedback; }

    public String getFacultyNotes() { return facultyNotes; }
    public void setFacultyNotes(String facultyNotes) { this.facultyNotes = facultyNotes; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public static SubmissionDtoBuilder builder() {
        return new SubmissionDtoBuilder();
    }

    public static class SubmissionDtoBuilder {
        private Long id;
        private Long assignmentId;
        private String assignmentTitle;
        private String assignmentRubric;
        private Integer assignmentMaxScore;
        private String courseCode;
        private String courseTitle;
        private Long studentId;
        private String studentName;
        private String studentCode;
        private String studentEmail;
        private String content;
        private LocalDateTime submissionDate;
        private Integer score;
        private String aiFeedback;
        private String facultyNotes;
        private String status;

        public SubmissionDtoBuilder id(Long id) { this.id = id; return this; }
        public SubmissionDtoBuilder assignmentId(Long assignmentId) { this.assignmentId = assignmentId; return this; }
        public SubmissionDtoBuilder assignmentTitle(String assignmentTitle) { this.assignmentTitle = assignmentTitle; return this; }
        public SubmissionDtoBuilder assignmentRubric(String assignmentRubric) { this.assignmentRubric = assignmentRubric; return this; }
        public SubmissionDtoBuilder assignmentMaxScore(Integer assignmentMaxScore) { this.assignmentMaxScore = assignmentMaxScore; return this; }
        public SubmissionDtoBuilder courseCode(String courseCode) { this.courseCode = courseCode; return this; }
        public SubmissionDtoBuilder courseTitle(String courseTitle) { this.courseTitle = courseTitle; return this; }
        public SubmissionDtoBuilder studentId(Long studentId) { this.studentId = studentId; return this; }
        public SubmissionDtoBuilder studentName(String studentName) { this.studentName = studentName; return this; }
        public SubmissionDtoBuilder studentCode(String studentCode) { this.studentCode = studentCode; return this; }
        public SubmissionDtoBuilder studentEmail(String studentEmail) { this.studentEmail = studentEmail; return this; }
        public SubmissionDtoBuilder content(String content) { this.content = content; return this; }
        public SubmissionDtoBuilder submissionDate(LocalDateTime submissionDate) { this.submissionDate = submissionDate; return this; }
        public SubmissionDtoBuilder score(Integer score) { this.score = score; return this; }
        public SubmissionDtoBuilder aiFeedback(String aiFeedback) { this.aiFeedback = aiFeedback; return this; }
        public SubmissionDtoBuilder facultyNotes(String facultyNotes) { this.facultyNotes = facultyNotes; return this; }
        public SubmissionDtoBuilder status(String status) { this.status = status; return this; }

        public SubmissionDto build() {
            SubmissionDto dto = new SubmissionDto();
            dto.setId(this.id);
            dto.setAssignmentId(this.assignmentId);
            dto.setAssignmentTitle(this.assignmentTitle);
            dto.setAssignmentRubric(this.assignmentRubric);
            dto.setAssignmentMaxScore(this.assignmentMaxScore);
            dto.setCourseCode(this.courseCode);
            dto.setCourseTitle(this.courseTitle);
            dto.setStudentId(this.studentId);
            dto.setStudentName(this.studentName);
            dto.setStudentCode(this.studentCode);
            dto.setStudentEmail(this.studentEmail);
            dto.setContent(this.content);
            dto.setSubmissionDate(this.submissionDate);
            dto.setScore(this.score);
            dto.setAiFeedback(this.aiFeedback);
            dto.setFacultyNotes(this.facultyNotes);
            dto.setStatus(this.status);
            return dto;
        }
    }
}
