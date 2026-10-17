package com.anurag.cse.controller;

import com.anurag.cse.dto.ApiResponse;
import com.anurag.cse.dto.AssignmentDto;
import com.anurag.cse.dto.SubmissionDto;
import com.anurag.cse.entity.Faculty;
import com.anurag.cse.entity.Student;
import com.anurag.cse.repository.FacultyRepository;
import com.anurag.cse.repository.StudentRepository;
import com.anurag.cse.service.AssignmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;

    public AssignmentController(AssignmentService assignmentService,
                                StudentRepository studentRepository,
                                FacultyRepository facultyRepository) {
        this.assignmentService = assignmentService;
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    private Long getStudentIdIfPresent(UserDetails userDetails) {
        if (userDetails != null) {
            return studentRepository.findByUserUsername(userDetails.getUsername())
                    .map(Student::getId)
                    .orElse(null);
        }
        return null;
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<ApiResponse<List<AssignmentDto>>> getAssignmentsByCourse(
            @PathVariable Long courseId,
            @AuthenticationPrincipal UserDetails userDetails) {
        Long studentId = getStudentIdIfPresent(userDetails);
        List<AssignmentDto> list = assignmentService.getAssignmentsByCourse(courseId, studentId);
        return ResponseEntity.ok(ApiResponse.success(list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AssignmentDto>> getAssignmentById(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        Long studentId = getStudentIdIfPresent(userDetails);
        AssignmentDto assignment = assignmentService.getAssignmentById(id, studentId);
        return ResponseEntity.ok(ApiResponse.success(assignment));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('FACULTY', 'ADMIN')")
    public ResponseEntity<ApiResponse<AssignmentDto>> createAssignment(@Valid @RequestBody AssignmentDto dto) {
        AssignmentDto created = assignmentService.createAssignment(dto);
        return ResponseEntity.ok(ApiResponse.success(created, "Assignment created successfully"));
    }

    @PostMapping("/{id}/submit")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<SubmissionDto>> submitAssignment(
            @PathVariable Long id,
            @RequestBody Map<String, String> payload,
            @AuthenticationPrincipal UserDetails userDetails) {

        Student student = studentRepository.findByUserUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Student record not found for: " + userDetails.getUsername()));

        String content = payload.get("content");
        if (content == null || content.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Submission content cannot be empty"));
        }

        SubmissionDto submission = assignmentService.submitAssignment(student.getId(), id, content);
        return ResponseEntity.ok(ApiResponse.success(submission, "Assignment submitted successfully"));
    }

    @GetMapping("/{id}/submissions")
    @PreAuthorize("hasAnyRole('FACULTY', 'ADMIN')")
    public ResponseEntity<ApiResponse<List<SubmissionDto>>> getSubmissionsByAssignment(@PathVariable Long id) {
        List<SubmissionDto> submissions = assignmentService.getSubmissionsByAssignment(id);
        return ResponseEntity.ok(ApiResponse.success(submissions));
    }

    @GetMapping("/student/my")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<List<SubmissionDto>>> getMySubmissions(
            @AuthenticationPrincipal UserDetails userDetails) {

        Student student = studentRepository.findByUserUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Student record not found for: " + userDetails.getUsername()));

        List<SubmissionDto> submissions = assignmentService.getSubmissionsByStudent(student.getId());
        return ResponseEntity.ok(ApiResponse.success(submissions));
    }

    @GetMapping("/faculty/my")
    @PreAuthorize("hasRole('FACULTY')")
    public ResponseEntity<ApiResponse<List<AssignmentDto>>> getFacultyAssignments(
            @AuthenticationPrincipal UserDetails userDetails) {

        Faculty faculty = facultyRepository.findByUserUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Faculty record not found for: " + userDetails.getUsername()));

        List<AssignmentDto> assignments = assignmentService.getAssignmentsByFaculty(faculty.getId());
        return ResponseEntity.ok(ApiResponse.success(assignments));
    }

    @PutMapping("/submissions/{submissionId}/grade")
    @PreAuthorize("hasAnyRole('FACULTY', 'ADMIN')")
    public ResponseEntity<ApiResponse<SubmissionDto>> gradeSubmission(
            @PathVariable Long submissionId,
            @RequestBody Map<String, Object> payload) {

        Integer score = payload.get("score") != null ? Integer.valueOf(payload.get("score").toString()) : null;
        String aiFeedback = payload.get("aiFeedback") != null ? payload.get("aiFeedback").toString() : null;
        String facultyNotes = payload.get("facultyNotes") != null ? payload.get("facultyNotes").toString() : null;

        SubmissionDto graded = assignmentService.gradeSubmission(submissionId, score, aiFeedback, facultyNotes);
        return ResponseEntity.ok(ApiResponse.success(graded, "Submission graded successfully"));
    }
}
