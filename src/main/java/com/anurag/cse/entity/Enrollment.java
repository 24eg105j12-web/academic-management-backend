package com.anurag.cse.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "enrollments", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"student_id", "course_id"})
})
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @CreationTimestamp
    @Column(name = "enrollment_date", updatable = false)
    private LocalDateTime enrollmentDate;

    @Column(length = 5)
    private String grade = "PENDING";

    @Column(length = 50)
    private String status = "ACTIVE"; // ACTIVE, COMPLETED, DROPPED

    public Enrollment() {}

    public Enrollment(Long id, Student student, Course course, LocalDateTime enrollmentDate, String grade, String status) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
        this.grade = grade != null ? grade : "PENDING";
        this.status = status != null ? status : "ACTIVE";
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public LocalDateTime getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(LocalDateTime enrollmentDate) { this.enrollmentDate = enrollmentDate; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public static EnrollmentBuilder builder() {
        return new EnrollmentBuilder();
    }

    public static class EnrollmentBuilder {
        private Long id;
        private Student student;
        private Course course;
        private LocalDateTime enrollmentDate;
        private String grade = "PENDING";
        private String status = "ACTIVE";

        public EnrollmentBuilder id(Long id) { this.id = id; return this; }
        public EnrollmentBuilder student(Student student) { this.student = student; return this; }
        public EnrollmentBuilder course(Course course) { this.course = course; return this; }
        public EnrollmentBuilder enrollmentDate(LocalDateTime enrollmentDate) { this.enrollmentDate = enrollmentDate; return this; }
        public EnrollmentBuilder grade(String grade) { this.grade = grade; return this; }
        public EnrollmentBuilder status(String status) { this.status = status; return this; }

        public Enrollment build() {
            Enrollment e = new Enrollment();
            e.setId(this.id);
            e.setStudent(this.student);
            e.setCourse(this.course);
            e.setEnrollmentDate(this.enrollmentDate);
            e.setGrade(this.grade != null ? this.grade : "PENDING");
            e.setStatus(this.status != null ? this.status : "ACTIVE");
            return e;
        }
    }
}
