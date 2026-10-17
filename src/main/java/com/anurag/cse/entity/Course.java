package com.anurag.cse.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Integer credits = 3;

    @Column(nullable = false, length = 100)
    private String department;

    @Column(nullable = false)
    private Integer semester = 1;

    @Lob
    @Column(name = "syllabus_text", columnDefinition = "LONGTEXT")
    private String syllabusText;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public Course() {}

    public Course(Long id, String code, String title, String description, Integer credits,
                  String department, Integer semester, String syllabusText, Faculty faculty, LocalDateTime createdAt) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.description = description;
        this.credits = credits != null ? credits : 3;
        this.department = department;
        this.semester = semester != null ? semester : 1;
        this.syllabusText = syllabusText;
        this.faculty = faculty;
        this.createdAt = createdAt;
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

    public Faculty getFaculty() { return faculty; }
    public void setFaculty(Faculty faculty) { this.faculty = faculty; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public static CourseBuilder builder() {
        return new CourseBuilder();
    }

    public static class CourseBuilder {
        private Long id;
        private String code;
        private String title;
        private String description;
        private Integer credits = 3;
        private String department;
        private Integer semester = 1;
        private String syllabusText;
        private Faculty faculty;
        private LocalDateTime createdAt;

        public CourseBuilder id(Long id) { this.id = id; return this; }
        public CourseBuilder code(String code) { this.code = code; return this; }
        public CourseBuilder title(String title) { this.title = title; return this; }
        public CourseBuilder description(String description) { this.description = description; return this; }
        public CourseBuilder credits(Integer credits) { this.credits = credits; return this; }
        public CourseBuilder department(String department) { this.department = department; return this; }
        public CourseBuilder semester(Integer semester) { this.semester = semester; return this; }
        public CourseBuilder syllabusText(String syllabusText) { this.syllabusText = syllabusText; return this; }
        public CourseBuilder faculty(Faculty faculty) { this.faculty = faculty; return this; }
        public CourseBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }

        public Course build() {
            Course c = new Course();
            c.setId(this.id);
            c.setCode(this.code);
            c.setTitle(this.title);
            c.setDescription(this.description);
            c.setCredits(this.credits != null ? this.credits : 3);
            c.setDepartment(this.department);
            c.setSemester(this.semester != null ? this.semester : 1);
            c.setSyllabusText(this.syllabusText);
            c.setFaculty(this.faculty);
            c.setCreatedAt(this.createdAt);
            return c;
        }
    }
}
