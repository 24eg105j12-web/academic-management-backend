package com.anurag.cse.dto;

import java.time.LocalDateTime;

public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
    private Boolean active;
    private LocalDateTime createdAt;
    // Context details
    private String studentId;
    private String employeeId;
    private String department;
    private Integer semester;
    private Double cgpa;

    public UserDto() {}

    public UserDto(Long id, String username, String email, String firstName, String lastName,
                   String role, Boolean active, LocalDateTime createdAt, String studentId,
                   String employeeId, String department, Integer semester, Double cgpa) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.active = active;
        this.createdAt = createdAt;
        this.studentId = studentId;
        this.employeeId = employeeId;
        this.department = department;
        this.semester = semester;
        this.cgpa = cgpa;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public Integer getSemester() { return semester; }
    public void setSemester(Integer semester) { this.semester = semester; }

    public Double getCgpa() { return cgpa; }
    public void setCgpa(Double cgpa) { this.cgpa = cgpa; }

    public static UserDtoBuilder builder() {
        return new UserDtoBuilder();
    }

    public static class UserDtoBuilder {
        private Long id;
        private String username;
        private String email;
        private String firstName;
        private String lastName;
        private String role;
        private Boolean active;
        private LocalDateTime createdAt;
        private String studentId;
        private String employeeId;
        private String department;
        private Integer semester;
        private Double cgpa;

        public UserDtoBuilder id(Long id) { this.id = id; return this; }
        public UserDtoBuilder username(String username) { this.username = username; return this; }
        public UserDtoBuilder email(String email) { this.email = email; return this; }
        public UserDtoBuilder firstName(String firstName) { this.firstName = firstName; return this; }
        public UserDtoBuilder lastName(String lastName) { this.lastName = lastName; return this; }
        public UserDtoBuilder role(String role) { this.role = role; return this; }
        public UserDtoBuilder active(Boolean active) { this.active = active; return this; }
        public UserDtoBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public UserDtoBuilder studentId(String studentId) { this.studentId = studentId; return this; }
        public UserDtoBuilder employeeId(String employeeId) { this.employeeId = employeeId; return this; }
        public UserDtoBuilder department(String department) { this.department = department; return this; }
        public UserDtoBuilder semester(Integer semester) { this.semester = semester; return this; }
        public UserDtoBuilder cgpa(Double cgpa) { this.cgpa = cgpa; return this; }

        public UserDto build() {
            UserDto dto = new UserDto();
            dto.setId(this.id);
            dto.setUsername(this.username);
            dto.setEmail(this.email);
            dto.setFirstName(this.firstName);
            dto.setLastName(this.lastName);
            dto.setRole(this.role);
            dto.setActive(this.active);
            dto.setCreatedAt(this.createdAt);
            dto.setStudentId(this.studentId);
            dto.setEmployeeId(this.employeeId);
            dto.setDepartment(this.department);
            dto.setSemester(this.semester);
            dto.setCgpa(this.cgpa);
            return dto;
        }
    }
}
