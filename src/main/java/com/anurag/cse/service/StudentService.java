package com.anurag.cse.service;

import com.anurag.cse.dto.UserDto;
import com.anurag.cse.entity.Student;
import com.anurag.cse.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional(readOnly = true)
    public List<UserDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        return mapToUserDto(student);
    }

    @Transactional(readOnly = true)
    public UserDto getStudentByUsername(String username) {
        Student student = studentRepository.findByUserUsername(username)
                .orElseThrow(() -> new RuntimeException("Student not found for username: " + username));
        return mapToUserDto(student);
    }

    @Transactional
    public UserDto updateStudentProfile(Long id, Integer semester, String phone, Double cgpa) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        if (semester != null) student.setSemester(semester);
        if (phone != null) student.setPhone(phone);
        if (cgpa != null) student.setCgpa(cgpa);

        Student updated = studentRepository.save(student);
        return mapToUserDto(updated);
    }

    private UserDto mapToUserDto(Student student) {
        return UserDto.builder()
                .id(student.getUser().getId())
                .username(student.getUser().getUsername())
                .email(student.getUser().getEmail())
                .firstName(student.getUser().getFirstName())
                .lastName(student.getUser().getLastName())
                .role("ROLE_STUDENT")
                .active(student.getUser().getActive())
                .studentId(student.getStudentId())
                .department(student.getDepartment())
                .semester(student.getSemester())
                .cgpa(student.getCgpa())
                .createdAt(student.getUser().getCreatedAt())
                .build();
    }
}
