package com.anurag.cse.service;

import com.anurag.cse.dto.EnrollmentDto;
import com.anurag.cse.entity.Course;
import com.anurag.cse.entity.Enrollment;
import com.anurag.cse.entity.Student;
import com.anurag.cse.repository.CourseRepository;
import com.anurag.cse.repository.EnrollmentRepository;
import com.anurag.cse.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             StudentRepository studentRepository,
                             CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    public EnrollmentDto enrollCourse(Long studentId, Long courseId) {
        if (enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId)) {
            throw new RuntimeException("Student is already enrolled in this course");
        }

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));

        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .grade("PENDING")
                .status("ACTIVE")
                .build();

        Enrollment saved = enrollmentRepository.save(enrollment);
        return mapToDto(saved);
    }

    @Transactional
    public void dropCourse(Long studentId, Long courseId) {
        Enrollment enrollment = enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found for student " + studentId + " and course " + courseId));

        enrollment.setStatus("DROPPED");
        enrollmentRepository.save(enrollment);
    }

    @Transactional(readOnly = true)
    public List<EnrollmentDto> getEnrollmentsByStudent(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId).stream()
                .filter(e -> !"DROPPED".equalsIgnoreCase(e.getStatus()))
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EnrollmentDto> getEnrollmentsByCourse(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public EnrollmentDto updateGrade(Long enrollmentId, String grade) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found with id: " + enrollmentId));

        enrollment.setGrade(grade.toUpperCase());
        enrollment.setStatus("COMPLETED");
        Enrollment saved = enrollmentRepository.save(enrollment);
        return mapToDto(saved);
    }

    private EnrollmentDto mapToDto(Enrollment enrollment) {
        return EnrollmentDto.builder()
                .id(enrollment.getId())
                .studentId(enrollment.getStudent().getId())
                .studentName(enrollment.getStudent().getUser().getFirstName() + " " + enrollment.getStudent().getUser().getLastName())
                .studentCode(enrollment.getStudent().getStudentId())
                .studentEmail(enrollment.getStudent().getUser().getEmail())
                .courseId(enrollment.getCourse().getId())
                .courseCode(enrollment.getCourse().getCode())
                .courseTitle(enrollment.getCourse().getTitle())
                .courseCredits(enrollment.getCourse().getCredits())
                .enrollmentDate(enrollment.getEnrollmentDate())
                .grade(enrollment.getGrade())
                .status(enrollment.getStatus())
                .build();
    }
}
