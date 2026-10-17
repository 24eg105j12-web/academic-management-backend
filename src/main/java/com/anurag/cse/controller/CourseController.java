package com.anurag.cse.controller;

import com.anurag.cse.dto.ApiResponse;
import com.anurag.cse.dto.CourseDto;
import com.anurag.cse.entity.Student;
import com.anurag.cse.repository.StudentRepository;
import com.anurag.cse.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;
    private final StudentRepository studentRepository;

    public CourseController(CourseService courseService, StudentRepository studentRepository) {
        this.courseService = courseService;
        this.studentRepository = studentRepository;
    }

    private Long getStudentIdIfPresent(UserDetails userDetails) {
        if (userDetails != null) {
            return studentRepository.findByUserUsername(userDetails.getUsername())
                    .map(Student::getId)
                    .orElse(null);
        }
        return null;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CourseDto>>> getAllCourses(@AuthenticationPrincipal UserDetails userDetails) {
        Long studentId = getStudentIdIfPresent(userDetails);
        List<CourseDto> courses = courseService.getAllCourses(studentId);
        return ResponseEntity.ok(ApiResponse.success(courses));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseDto>> getCourseById(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        Long studentId = getStudentIdIfPresent(userDetails);
        CourseDto course = courseService.getCourseById(id, studentId);
        return ResponseEntity.ok(ApiResponse.success(course));
    }

    @GetMapping("/faculty/{facultyId}")
    public ResponseEntity<ApiResponse<List<CourseDto>>> getCoursesByFaculty(@PathVariable Long facultyId) {
        List<CourseDto> courses = courseService.getCoursesByFaculty(facultyId);
        return ResponseEntity.ok(ApiResponse.success(courses));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')")
    public ResponseEntity<ApiResponse<CourseDto>> createCourse(@Valid @RequestBody CourseDto courseDto) {
        CourseDto created = courseService.createCourse(courseDto);
        return ResponseEntity.ok(ApiResponse.success(created, "Course created successfully"));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')")
    public ResponseEntity<ApiResponse<CourseDto>> updateCourse(@PathVariable Long id, @Valid @RequestBody CourseDto courseDto) {
        CourseDto updated = courseService.updateCourse(id, courseDto);
        return ResponseEntity.ok(ApiResponse.success(updated, "Course updated successfully"));
    }

    @PutMapping("/{id}/syllabus")
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')")
    public ResponseEntity<ApiResponse<CourseDto>> updateSyllabus(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        String syllabus = payload.get("syllabusText");
        CourseDto updated = courseService.updateSyllabus(id, syllabus);
        return ResponseEntity.ok(ApiResponse.success(updated, "Syllabus updated successfully"));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Course deleted successfully"));
    }
}
