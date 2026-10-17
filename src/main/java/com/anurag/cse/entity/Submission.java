package com.anurag.cse.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "submissions", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"assignment_id", "student_id"})
})
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Lob
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String content;

    @CreationTimestamp
    @Column(name = "submission_date", updatable = false)
    private LocalDateTime submissionDate;

    private Integer score;

    @Lob
    @Column(name = "ai_feedback", columnDefinition = "LONGTEXT")
    private String aiFeedback;

    @Lob
    @Column(name = "faculty_notes", columnDefinition = "TEXT")
    private String facultyNotes;

    @Column(length = 50)
    private String status = "SUBMITTED"; // SUBMITTED, GRADED, RESUBMITTED

    public Submission() {}

    public Submission(Long id, Assignment assignment, Student student, String content,
                      LocalDateTime submissionDate, Integer score, String aiFeedback,
                      String facultyNotes, String status) {
        this.id = id;
        this.assignment = assignment;
        this.student = student;
        this.content = content;
        this.submissionDate = submissionDate;
        this.score = score;
        this.aiFeedback = aiFeedback;
        this.facultyNotes = facultyNotes;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Assignment getAssignment() { return assignment; }
    public void setAssignment(Assignment assignment) { this.assignment = assignment; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

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

    public static SubmissionBuilder builder() {
        return new SubmissionBuilder();
    }

    public static class SubmissionBuilder {
        private Long id;
        private Assignment assignment;
        private Student student;
        private String content;
        private LocalDateTime submissionDate;
        private Integer score;
        private String aiFeedback;
        private String facultyNotes;
        private String status = "SUBMITTED";

        public SubmissionBuilder id(Long id) { this.id = id; return this; }
        public SubmissionBuilder assignment(Assignment assignment) { this.assignment = assignment; return this; }
        public SubmissionBuilder student(Student student) { this.student = student; return this; }
        public SubmissionBuilder content(String content) { this.content = content; return this; }
        public SubmissionBuilder submissionDate(LocalDateTime submissionDate) { this.submissionDate = submissionDate; return this; }
        public SubmissionBuilder score(Integer score) { this.score = score; return this; }
        public SubmissionBuilder aiFeedback(String aiFeedback) { this.aiFeedback = aiFeedback; return this; }
        public SubmissionBuilder facultyNotes(String facultyNotes) { this.facultyNotes = facultyNotes; return this; }
        public SubmissionBuilder status(String status) { this.status = status; return this; }

        public Submission build() {
            Submission s = new Submission();
            s.setId(this.id);
            s.setAssignment(this.assignment);
            s.setStudent(this.student);
            s.setContent(this.content);
            s.setSubmissionDate(this.submissionDate);
            s.setScore(this.score);
            s.setAiFeedback(this.aiFeedback);
            s.setFacultyNotes(this.facultyNotes);
            s.setStatus(this.status != null ? this.status : "SUBMITTED");
            return s;
        }
    }
}
