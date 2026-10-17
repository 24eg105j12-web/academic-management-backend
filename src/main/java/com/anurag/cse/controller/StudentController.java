package com.anurag.cse.controller;

import com.anurag.cse.dto.ApiResponse;
import com.anurag.cse.dto.UserDto;
import com.anurag.cse.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')")
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllStudents() {
        List<UserDto> students = studentService.getAllStudents();
        return ResponseEntity.ok(ApiResponse.success(students));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> getStudentById(@PathVariable Long id) {
        UserDto student = studentService.getStudentById(id);
        return ResponseEntity.ok(ApiResponse.success(student));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STUDENT')")
    public ResponseEntity<ApiResponse<UserDto>> updateStudentProfile(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        Integer semester = payload.get("semester") != null ? Integer.valueOf(payload.get("semester").toString()) : null;
        String phone = payload.get("phone") != null ? payload.get("phone").toString() : null;
        Double cgpa = payload.get("cgpa") != null ? Double.valueOf(payload.get("cgpa").toString()) : null;

        UserDto updated = studentService.updateStudentProfile(id, semester, phone, cgpa);
        return ResponseEntity.ok(ApiResponse.success(updated, "Student profile updated"));
    }
}
