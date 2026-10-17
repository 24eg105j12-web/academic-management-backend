package com.anurag.cse.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "faculties")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true)
    private User user;

    @Column(name = "employee_id", nullable = false, unique = true, length = 50)
    private String employeeId;

    @Column(nullable = false, length = 100)
    private String department;

    @Column(length = 100)
    private String designation;

    @Column(name = "office_hours", length = 100)
    private String officeHours;

    public Faculty() {}

    public Faculty(Long id, User user, String employeeId, String department, String designation, String officeHours) {
        this.id = id;
        this.user = user;
        this.employeeId = employeeId;
        this.department = department;
        this.designation = designation;
        this.officeHours = officeHours;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public String getOfficeHours() { return officeHours; }
    public void setOfficeHours(String officeHours) { this.officeHours = officeHours; }

    public static FacultyBuilder builder() {
        return new FacultyBuilder();
    }

    public static class FacultyBuilder {
        private Long id;
        private User user;
        private String employeeId;
        private String department;
        private String designation;
        private String officeHours;

        public FacultyBuilder id(Long id) { this.id = id; return this; }
        public FacultyBuilder user(User user) { this.user = user; return this; }
        public FacultyBuilder employeeId(String employeeId) { this.employeeId = employeeId; return this; }
        public FacultyBuilder department(String department) { this.department = department; return this; }
        public FacultyBuilder designation(String designation) { this.designation = designation; return this; }
        public FacultyBuilder officeHours(String officeHours) { this.officeHours = officeHours; return this; }

        public Faculty build() {
            Faculty f = new Faculty();
            f.setId(this.id);
            f.setUser(this.user);
            f.setEmployeeId(this.employeeId);
            f.setDepartment(this.department);
            f.setDesignation(this.designation);
            f.setOfficeHours(this.officeHours);
            return f;
        }
    }
}
