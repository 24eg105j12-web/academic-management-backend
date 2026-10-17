package com.anurag.cse.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class CourseDto {
    private Long id;

    @NotBlank(message = "Course code is required")
    private String code;

    @NotBlank(message = "Course title is required")
    private String title;

    private String description;

    @NotNull(message = "Credits are required")
    private Integer credits;

    @NotBlank(message = "Department is required")
    private String department;

    private Integer semester;

    private String syllabusText;

    private Long facultyId;
    private String facultyName;
    private String facultyEmail;

    private LocalDateTime createdAt;
    private Integer enrolledStudentsCount;
    private Boolean isEnrolled; // Context for student viewing course

    public CourseDto() {}

    public CourseDto(Long id, String code, String title, String description, Integer credits,
                     String department, Integer semester, String syllabusText, Long facultyId,
                     String facultyName, String facultyEmail, LocalDateTime createdAt,
                     Integer enrolledStudentsCount, Boolean isEnrolled) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.description = description;
        this.credits = credits;
        this.department = department;
        this.semester = semester;
        this.syllabusText = syllabusText;
        this.facultyId = facultyId;
        this.facultyName = facultyName;
        this.facultyEmail = facultyEmail;
        this.createdAt = createdAt;
        this.enrolledStudentsCount = enrolledStudentsCount;
        this.isEnrolled = isEnrolled;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getCredits() { return credits; }
    public void setCredits(Integer credits) { this.credits = credits; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public Integer getSemester() { return semester; }
    public void setSemester(Integer semester) { this.semester = semester; }

    public String getSyllabusText() { return syllabusText; }
    public void setSyllabusText(String syllabusText) { this.syllabusText = syllabusText; }

    public Long getFacultyId() { return facultyId; }
    public void setFacultyId(Long facultyId) { this.facultyId = facultyId; }

    public String getFacultyName() { return facultyName; }
    public void setFacultyName(String facultyName) { this.facultyName = facultyName; }

    public String getFacultyEmail() { return facultyEmail; }
    public void setFacultyEmail(String facultyEmail) { this.facultyEmail = facultyEmail; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Integer getEnrolledStudentsCount() { return enrolledStudentsCount; }
    public void setEnrolledStudentsCount(Integer enrolledStudentsCount) { this.enrolledStudentsCount = enrolledStudentsCount; }

    public Boolean getIsEnrolled() { return isEnrolled; }
    public void setIsEnrolled(Boolean isEnrolled) { this.isEnrolled = isEnrolled; }

    public static CourseDtoBuilder builder() {
        return new CourseDtoBuilder();
    }

    public static class CourseDtoBuilder {
        private Long id;
        private String code;
        private String title;
        private String description;
        private Integer credits;
        private String department;
        private Integer semester;
        private String syllabusText;
        private Long facultyId;
        private String facultyName;
        private String facultyEmail;
        private LocalDateTime createdAt;
        private Integer enrolledStudentsCount;
        private Boolean isEnrolled;

        public CourseDtoBuilder id(Long id) { this.id = id; return this; }
        public CourseDtoBuilder code(String code) { this.code = code; return this; }
        public CourseDtoBuilder title(String title) { this.title = title; return this; }
        public CourseDtoBuilder description(String description) { this.description = description; return this; }
        public CourseDtoBuilder credits(Integer credits) { this.credits = credits; return this; }
        public CourseDtoBuilder department(String department) { this.department = department; return this; }
        public CourseDtoBuilder semester(Integer semester) { this.semester = semester; return this; }
        public CourseDtoBuilder syllabusText(String syllabusText) { this.syllabusText = syllabusText; return this; }
        public CourseDtoBuilder facultyId(Long facultyId) { this.facultyId = facultyId; return this; }
        public CourseDtoBuilder facultyName(String facultyName) { this.facultyName = facultyName; return this; }
        public CourseDtoBuilder facultyEmail(String facultyEmail) { this.facultyEmail = facultyEmail; return this; }
        public CourseDtoBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public CourseDtoBuilder enrolledStudentsCount(Integer enrolledStudentsCount) { this.enrolledStudentsCount = enrolledStudentsCount; return this; }
        public CourseDtoBuilder isEnrolled(Boolean isEnrolled) { this.isEnrolled = isEnrolled; return this; }

        public CourseDto build() {
            CourseDto dto = new CourseDto();
            dto.setId(this.id);
            dto.setCode(this.code);
            dto.setTitle(this.title);
            dto.setDescription(this.description);
            dto.setCredits(this.credits);
            dto.setDepartment(this.department);
            dto.setSemester(this.semester);
            dto.setSyllabusText(this.syllabusText);
            dto.setFacultyId(this.facultyId);
            dto.setFacultyName(this.facultyName);
            dto.setFacultyEmail(this.facultyEmail);
            dto.setCreatedAt(this.createdAt);
            dto.setEnrolledStudentsCount(this.enrolledStudentsCount);
            dto.setIsEnrolled(this.isEnrolled);
            return dto;
        }
    }
}
