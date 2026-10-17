package com.anurag.cse.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class AssignmentDto {
    private Long id;

    @NotNull(message = "Course ID is required")
    private Long courseId;
    private String courseCode;
    private String courseTitle;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Rubric is required")
    private String rubric;

    private Integer maxScore = 100;

    private LocalDateTime dueDate;
    private LocalDateTime createdAt;

    // Student view context
    private Boolean isSubmitted;
    private Long userSubmissionId;
    private Integer userScore;
    private String userStatus;
    private Integer totalSubmissions;

    public AssignmentDto() {}

    public AssignmentDto(Long id, Long courseId, String courseCode, String courseTitle,
                         String title, String description, String rubric, Integer maxScore,
                         LocalDateTime dueDate, LocalDateTime createdAt, Boolean isSubmitted,
                         Long userSubmissionId, Integer userScore, String userStatus,
                         Integer totalSubmissions) {
        this.id = id;
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.title = title;
        this.description = description;
        this.rubric = rubric;
        this.maxScore = maxScore != null ? maxScore : 100;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
        this.isSubmitted = isSubmitted;
        this.userSubmissionId = userSubmissionId;
        this.userScore = userScore;
        this.userStatus = userStatus;
        this.totalSubmissions = totalSubmissions;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public String getCourseTitle() { return courseTitle; }
    public void setCourseTitle(String courseTitle) { this.courseTitle = courseTitle; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getRubric() { return rubric; }
    public void setRubric(String rubric) { this.rubric = rubric; }

    public Integer getMaxScore() { return maxScore; }
    public void setMaxScore(Integer maxScore) { this.maxScore = maxScore; }

    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Boolean getIsSubmitted() { return isSubmitted; }
    public void setIsSubmitted(Boolean isSubmitted) { this.isSubmitted = isSubmitted; }

    public Long getUserSubmissionId() { return userSubmissionId; }
    public void setUserSubmissionId(Long userSubmissionId) { this.userSubmissionId = userSubmissionId; }

    public Integer getUserScore() { return userScore; }
    public void setUserScore(Integer userScore) { this.userScore = userScore; }

    public String getUserStatus() { return userStatus; }
    public void setUserStatus(String userStatus) { this.userStatus = userStatus; }

    public Integer getTotalSubmissions() { return totalSubmissions; }
    public void setTotalSubmissions(Integer totalSubmissions) { this.totalSubmissions = totalSubmissions; }

    public static AssignmentDtoBuilder builder() {
        return new AssignmentDtoBuilder();
    }

    public static class AssignmentDtoBuilder {
        private Long id;
        private Long courseId;
        private String courseCode;
        private String courseTitle;
        private String title;
        private String description;
        private String rubric;
        private Integer maxScore = 100;
        private LocalDateTime dueDate;
        private LocalDateTime createdAt;
        private Boolean isSubmitted;
        private Long userSubmissionId;
        private Integer userScore;
        private String userStatus;
        private Integer totalSubmissions;

        public AssignmentDtoBuilder id(Long id) { this.id = id; return this; }
        public AssignmentDtoBuilder courseId(Long courseId) { this.courseId = courseId; return this; }
        public AssignmentDtoBuilder courseCode(String courseCode) { this.courseCode = courseCode; return this; }
        public AssignmentDtoBuilder courseTitle(String courseTitle) { this.courseTitle = courseTitle; return this; }
        public AssignmentDtoBuilder title(String title) { this.title = title; return this; }
        public AssignmentDtoBuilder description(String description) { this.description = description; return this; }
        public AssignmentDtoBuilder rubric(String rubric) { this.rubric = rubric; return this; }
        public AssignmentDtoBuilder maxScore(Integer maxScore) { this.maxScore = maxScore; return this; }
        public AssignmentDtoBuilder dueDate(LocalDateTime dueDate) { this.dueDate = dueDate; return this; }
        public AssignmentDtoBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public AssignmentDtoBuilder isSubmitted(Boolean isSubmitted) { this.isSubmitted = isSubmitted; return this; }
        public AssignmentDtoBuilder userSubmissionId(Long userSubmissionId) { this.userSubmissionId = userSubmissionId; return this; }
        public AssignmentDtoBuilder userScore(Integer userScore) { this.userScore = userScore; return this; }
        public AssignmentDtoBuilder userStatus(String userStatus) { this.userStatus = userStatus; return this; }
        public AssignmentDtoBuilder totalSubmissions(Integer totalSubmissions) { this.totalSubmissions = totalSubmissions; return this; }

        public AssignmentDto build() {
            AssignmentDto dto = new AssignmentDto();
            dto.setId(this.id);
            dto.setCourseId(this.courseId);
            dto.setCourseCode(this.courseCode);
            dto.setCourseTitle(this.courseTitle);
            dto.setTitle(this.title);
            dto.setDescription(this.description);
            dto.setRubric(this.rubric);
            dto.setMaxScore(this.maxScore != null ? this.maxScore : 100);
            dto.setDueDate(this.dueDate);
            dto.setCreatedAt(this.createdAt);
            dto.setIsSubmitted(this.isSubmitted);
            dto.setUserSubmissionId(this.userSubmissionId);
            dto.setUserScore(this.userScore);
            dto.setUserStatus(this.userStatus);
            dto.setTotalSubmissions(this.totalSubmissions);
            return dto;
        }
    }
}
