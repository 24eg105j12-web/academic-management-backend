package com.anurag.cse.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true)
    private User user;

    @Column(name = "student_id", nullable = false, unique = true, length = 50)
    private String studentId;

    @Column(nullable = false, length = 100)
    private String department;

    @Column(nullable = false)
    private Integer semester = 1;

    @Column(name = "cgpa")
    private Double cgpa = 0.0;

    @Column(length = 20)
    private String phone;

    public Student() {}

    public Student(Long id, User user, String studentId, String department, Integer semester, Double cgpa, String phone) {
        this.id = id;
        this.user = user;
        this.studentId = studentId;
        this.department = department;
        this.semester = semester != null ? semester : 1;
        this.cgpa = cgpa != null ? cgpa : 0.0;
        this.phone = phone;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public Integer getSemester() { return semester; }
    public void setSemester(Integer semester) { this.semester = semester; }

    public Double getCgpa() { return cgpa; }
    public void setCgpa(Double cgpa) { this.cgpa = cgpa; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public static StudentBuilder builder() {
        return new StudentBuilder();
    }

    public static class StudentBuilder {
        private Long id;
        private User user;
        private String studentId;
        private String department;
        private Integer semester = 1;
        private Double cgpa = 0.0;
        private String phone;

        public StudentBuilder id(Long id) { this.id = id; return this; }
        public StudentBuilder user(User user) { this.user = user; return this; }
        public StudentBuilder studentId(String studentId) { this.studentId = studentId; return this; }
        public StudentBuilder department(String department) { this.department = department; return this; }
        public StudentBuilder semester(Integer semester) { this.semester = semester; return this; }
        public StudentBuilder cgpa(Double cgpa) { this.cgpa = cgpa; return this; }
        public StudentBuilder phone(String phone) { this.phone = phone; return this; }

        public Student build() {
            Student s = new Student();
            s.setId(this.id);
            s.setUser(this.user);
            s.setStudentId(this.studentId);
            s.setDepartment(this.department);
            s.setSemester(this.semester != null ? this.semester : 1);
            s.setCgpa(this.cgpa != null ? this.cgpa : 0.0);
            s.setPhone(this.phone);
            return s;
        }
    }
}
