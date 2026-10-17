package com.anurag.cse.service;

import com.anurag.cse.dto.AssignmentDto;
import com.anurag.cse.dto.SubmissionDto;
import com.anurag.cse.entity.Assignment;
import com.anurag.cse.entity.Course;
import com.anurag.cse.entity.Student;
import com.anurag.cse.entity.Submission;
import com.anurag.cse.repository.AssignmentRepository;
import com.anurag.cse.repository.CourseRepository;
import com.anurag.cse.repository.StudentRepository;
import com.anurag.cse.repository.SubmissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final SubmissionRepository submissionRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public AssignmentService(AssignmentRepository assignmentRepository,
                             SubmissionRepository submissionRepository,
                             CourseRepository courseRepository,
                             StudentRepository studentRepository) {
        this.assignmentRepository = assignmentRepository;
        this.submissionRepository = submissionRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public AssignmentDto createAssignment(AssignmentDto dto) {
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + dto.getCourseId()));

        Assignment assignment = Assignment.builder()
                .course(course)
                .title(dto.getTitle())
                .description(dto.getDescription())
                .rubric(dto.getRubric())
                .maxScore(dto.getMaxScore() != null ? dto.getMaxScore() : 100)
                .dueDate(dto.getDueDate())
                .build();

        Assignment saved = assignmentRepository.save(assignment);
        return mapToDto(saved, null);
    }

    @Transactional(readOnly = true)
    public List<AssignmentDto> getAssignmentsByCourse(Long courseId, Long studentId) {
        return assignmentRepository.findByCourseId(courseId).stream()
                .map(a -> mapToDto(a, studentId))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AssignmentDto> getAssignmentsByFaculty(Long facultyId) {
        return assignmentRepository.findByCourseFacultyId(facultyId).stream()
                .map(a -> mapToDto(a, null))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AssignmentDto getAssignmentById(Long id, Long studentId) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found with id: " + id));
        return mapToDto(assignment, studentId);
    }

    @Transactional
    public SubmissionDto submitAssignment(Long studentId, Long assignmentId, String content) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found with id: " + assignmentId));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

        Submission submission = submissionRepository.findByAssignmentIdAndStudentId(assignmentId, studentId)
                .orElse(Submission.builder()
                        .assignment(assignment)
                        .student(student)
                        .build());

        submission.setContent(content);
        submission.setStatus("SUBMITTED");

        Submission saved = submissionRepository.save(submission);
        return mapToSubmissionDto(saved);
    }

    @Transactional(readOnly = true)
    public List<SubmissionDto> getSubmissionsByAssignment(Long assignmentId) {
        return submissionRepository.findByAssignmentId(assignmentId).stream()
                .map(this::mapToSubmissionDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<SubmissionDto> getSubmissionsByStudent(Long studentId) {
        return submissionRepository.findByStudentId(studentId).stream()
                .map(this::mapToSubmissionDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public SubmissionDto gradeSubmission(Long submissionId, Integer score, String aiFeedback, String facultyNotes) {
        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("Submission not found with id: " + submissionId));

        if (score != null) {
            submission.setScore(score);
        }
        if (aiFeedback != null) {
            submission.setAiFeedback(aiFeedback);
        }
        if (facultyNotes != null) {
            submission.setFacultyNotes(facultyNotes);
        }
        submission.setStatus("GRADED");

        Submission saved = submissionRepository.save(submission);
        return mapToSubmissionDto(saved);
    }

    private AssignmentDto mapToDto(Assignment assignment, Long studentId) {
        int submissionCount = submissionRepository.findByAssignmentId(assignment.getId()).size();

        Boolean isSubmitted = false;
        Long userSubmissionId = null;
        Integer userScore = null;
        String userStatus = null;

        if (studentId != null) {
            Optional<Submission> submission = submissionRepository.findByAssignmentIdAndStudentId(assignment.getId(), studentId);
            if (submission.isPresent()) {
                isSubmitted = true;
                userSubmissionId = submission.get().getId();
                userScore = submission.get().getScore();
                userStatus = submission.get().getStatus();
            }
        }

        return AssignmentDto.builder()
                .id(assignment.getId())
                .courseId(assignment.getCourse().getId())
                .courseCode(assignment.getCourse().getCode())
                .courseTitle(assignment.getCourse().getTitle())
                .title(assignment.getTitle())
                .description(assignment.getDescription())
                .rubric(assignment.getRubric())
                .maxScore(assignment.getMaxScore())
                .dueDate(assignment.getDueDate())
                .createdAt(assignment.getCreatedAt())
                .totalSubmissions(submissionCount)
                .isSubmitted(isSubmitted)
                .userSubmissionId(userSubmissionId)
                .userScore(userScore)
                .userStatus(userStatus)
                .build();
    }

    public SubmissionDto mapToSubmissionDto(Submission submission) {
        return SubmissionDto.builder()
                .id(submission.getId())
                .assignmentId(submission.getAssignment().getId())
                .assignmentTitle(submission.getAssignment().getTitle())
                .assignmentRubric(submission.getAssignment().getRubric())
                .assignmentMaxScore(submission.getAssignment().getMaxScore())
                .courseCode(submission.getAssignment().getCourse().getCode())
                .courseTitle(submission.getAssignment().getCourse().getTitle())
                .studentId(submission.getStudent().getId())
                .studentName(submission.getStudent().getUser().getFirstName() + " " + submission.getStudent().getUser().getLastName())
                .studentCode(submission.getStudent().getStudentId())
                .studentEmail(submission.getStudent().getUser().getEmail())
                .content(submission.getContent())
                .submissionDate(submission.getSubmissionDate())
                .score(submission.getScore())
                .aiFeedback(submission.getAiFeedback())
                .facultyNotes(submission.getFacultyNotes())
                .status(submission.getStatus())
                .build();
    }
}
