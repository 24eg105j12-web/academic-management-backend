package com.anurag.cse.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "assignments")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false, length = 200)
    private String title;

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String rubric;

    @Column(name = "max_score", nullable = false)
    private Integer maxScore = 100;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public Assignment() {}

    public Assignment(Long id, Course course, String title, String description, String rubric,
                      Integer maxScore, LocalDateTime dueDate, LocalDateTime createdAt) {
        this.id = id;
        this.course = course;
        this.title = title;
        this.description = description;
        this.rubric = rubric;
        this.maxScore = maxScore != null ? maxScore : 100;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

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

    public static AssignmentBuilder builder() {
        return new AssignmentBuilder();
    }

    public static class AssignmentBuilder {
        private Long id;
        private Course course;
        private String title;
        private String description;
        private String rubric;
        private Integer maxScore = 100;
        private LocalDateTime dueDate;
        private LocalDateTime createdAt;

        public AssignmentBuilder id(Long id) { this.id = id; return this; }
        public AssignmentBuilder course(Course course) { this.course = course; return this; }
        public AssignmentBuilder title(String title) { this.title = title; return this; }
        public AssignmentBuilder description(String description) { this.description = description; return this; }
        public AssignmentBuilder rubric(String rubric) { this.rubric = rubric; return this; }
        public AssignmentBuilder maxScore(Integer maxScore) { this.maxScore = maxScore; return this; }
        public AssignmentBuilder dueDate(LocalDateTime dueDate) { this.dueDate = dueDate; return this; }
        public AssignmentBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }

        public Assignment build() {
            Assignment a = new Assignment();
            a.setId(this.id);
            a.setCourse(this.course);
            a.setTitle(this.title);
            a.setDescription(this.description);
            a.setRubric(this.rubric);
            a.setMaxScore(this.maxScore != null ? this.maxScore : 100);
            a.setDueDate(this.dueDate);
            a.setCreatedAt(this.createdAt);
            return a;
        }
    }
}
