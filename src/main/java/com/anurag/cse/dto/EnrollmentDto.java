package com.anurag.cse.dto;

import java.time.LocalDateTime;

public class EnrollmentDto {
    private Long id;
    private Long studentId;
    private String studentName;
    private String studentCode;
    private String studentEmail;
    private Long courseId;
    private String courseCode;
    private String courseTitle;
    private Integer courseCredits;
    private LocalDateTime enrollmentDate;
    private String grade;
    private String status;

    public EnrollmentDto() {}

    public EnrollmentDto(Long id, Long studentId, String studentName, String studentCode,
                         String studentEmail, Long courseId, String courseCode, String courseTitle,
                         Integer courseCredits, LocalDateTime enrollmentDate, String grade, String status) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentCode = studentCode;
        this.studentEmail = studentEmail;
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.courseCredits = courseCredits;
        this.enrollmentDate = enrollmentDate;
        this.grade = grade;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getStudentCode() { return studentCode; }
    public void setStudentCode(String studentCode) { this.studentCode = studentCode; }

    public String getStudentEmail() { return studentEmail; }
    public void setStudentEmail(String studentEmail) { this.studentEmail = studentEmail; }

    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public String getCourseTitle() { return courseTitle; }
    public void setCourseTitle(String courseTitle) { this.courseTitle = courseTitle; }

    public Integer getCourseCredits() { return courseCredits; }
    public void setCourseCredits(Integer courseCredits) { this.courseCredits = courseCredits; }

    public LocalDateTime getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(LocalDateTime enrollmentDate) { this.enrollmentDate = enrollmentDate; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public static EnrollmentDtoBuilder builder() {
        return new EnrollmentDtoBuilder();
    }

    public static class EnrollmentDtoBuilder {
        private Long id;
        private Long studentId;
        private String studentName;
        private String studentCode;
        private String studentEmail;
        private Long courseId;
        private String courseCode;
        private String courseTitle;
        private Integer courseCredits;
        private LocalDateTime enrollmentDate;
        private String grade;
        private String status;

        public EnrollmentDtoBuilder id(Long id) { this.id = id; return this; }
        public EnrollmentDtoBuilder studentId(Long studentId) { this.studentId = studentId; return this; }
        public EnrollmentDtoBuilder studentName(String studentName) { this.studentName = studentName; return this; }
        public EnrollmentDtoBuilder studentCode(String studentCode) { this.studentCode = studentCode; return this; }
        public EnrollmentDtoBuilder studentEmail(String studentEmail) { this.studentEmail = studentEmail; return this; }
        public EnrollmentDtoBuilder courseId(Long courseId) { this.courseId = courseId; return this; }
        public EnrollmentDtoBuilder courseCode(String courseCode) { this.courseCode = courseCode; return this; }
        public EnrollmentDtoBuilder courseTitle(String courseTitle) { this.courseTitle = courseTitle; return this; }
        public EnrollmentDtoBuilder courseCredits(Integer courseCredits) { this.courseCredits = courseCredits; return this; }
        public EnrollmentDtoBuilder enrollmentDate(LocalDateTime enrollmentDate) { this.enrollmentDate = enrollmentDate; return this; }
        public EnrollmentDtoBuilder grade(String grade) { this.grade = grade; return this; }
        public EnrollmentDtoBuilder status(String status) { this.status = status; return this; }

        public EnrollmentDto build() {
            EnrollmentDto dto = new EnrollmentDto();
            dto.setId(this.id);
            dto.setStudentId(this.studentId);
            dto.setStudentName(this.studentName);
            dto.setStudentCode(this.studentCode);
            dto.setStudentEmail(this.studentEmail);
            dto.setCourseId(this.courseId);
            dto.setCourseCode(this.courseCode);
            dto.setCourseTitle(this.courseTitle);
            dto.setCourseCredits(this.courseCredits);
            dto.setEnrollmentDate(this.enrollmentDate);
            dto.setGrade(this.grade);
            dto.setStatus(this.status);
            return dto;
        }
    }
}
