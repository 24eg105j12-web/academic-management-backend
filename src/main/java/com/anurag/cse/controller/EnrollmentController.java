package com.anurag.cse.controller;

import com.anurag.cse.dto.ApiResponse;
import com.anurag.cse.dto.EnrollmentDto;
import com.anurag.cse.entity.Student;
import com.anurag.cse.repository.StudentRepository;
import com.anurag.cse.service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final StudentRepository studentRepository;

    public EnrollmentController(EnrollmentService enrollmentService, StudentRepository studentRepository) {
        this.enrollmentService = enrollmentService;
        this.studentRepository = studentRepository;
    }

    @PostMapping("/enroll/{courseId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<EnrollmentDto>> enrollInCourse(
            @PathVariable Long courseId,
            @AuthenticationPrincipal UserDetails userDetails) {

        Student student = studentRepository.findByUserUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Student record not found for user: " + userDetails.getUsername()));

        EnrollmentDto enrolled = enrollmentService.enrollCourse(student.getId(), courseId);
        return ResponseEntity.ok(ApiResponse.success(enrolled, "Successfully enrolled in course"));
    }

    @PostMapping("/drop/{courseId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<Void>> dropCourse(
            @PathVariable Long courseId,
            @AuthenticationPrincipal UserDetails userDetails) {

        Student student = studentRepository.findByUserUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Student record not found for user: " + userDetails.getUsername()));

        enrollmentService.dropCourse(student.getId(), courseId);
        return ResponseEntity.ok(ApiResponse.success(null, "Successfully dropped course"));
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<List<EnrollmentDto>>> getMyEnrollments(
            @AuthenticationPrincipal UserDetails userDetails) {

        Student student = studentRepository.findByUserUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Student record not found for user: " + userDetails.getUsername()));

        List<EnrollmentDto> list = enrollmentService.getEnrollmentsByStudent(student.getId());
        return ResponseEntity.ok(ApiResponse.success(list));
    }

    @GetMapping("/course/{courseId}")
    @PreAuthorize("hasAnyRole('FACULTY', 'ADMIN')")
    public ResponseEntity<ApiResponse<List<EnrollmentDto>>> getEnrollmentsByCourse(@PathVariable Long courseId) {
        List<EnrollmentDto> list = enrollmentService.getEnrollmentsByCourse(courseId);
        return ResponseEntity.ok(ApiResponse.success(list));
    }

    @PutMapping("/{id}/grade")
    @PreAuthorize("hasAnyRole('FACULTY', 'ADMIN')")
    public ResponseEntity<ApiResponse<EnrollmentDto>> updateGrade(
            @PathVariable Long id,
            @RequestBody Map<String, String> payload) {
        String grade = payload.get("grade");
        EnrollmentDto updated = enrollmentService.updateGrade(id, grade);
        return ResponseEntity.ok(ApiResponse.success(updated, "Grade updated successfully"));
    }
}
