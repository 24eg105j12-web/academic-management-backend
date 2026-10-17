package com.anurag.cse.service;

import com.anurag.cse.dto.CourseDto;
import com.anurag.cse.entity.Course;
import com.anurag.cse.entity.Faculty;
import com.anurag.cse.repository.CourseRepository;
import com.anurag.cse.repository.EnrollmentRepository;
import com.anurag.cse.repository.FacultyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final FacultyRepository facultyRepository;
    private final EnrollmentRepository enrollmentRepository;

    public CourseService(CourseRepository courseRepository,
                         FacultyRepository facultyRepository,
                         EnrollmentRepository enrollmentRepository) {
        this.courseRepository = courseRepository;
        this.facultyRepository = facultyRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Transactional(readOnly = true)
    public List<CourseDto> getAllCourses(Long currentStudentId) {
        return courseRepository.findAll().stream()
                .map(course -> mapToDto(course, currentStudentId))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CourseDto getCourseById(Long id, Long currentStudentId) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
        return mapToDto(course, currentStudentId);
    }

    @Transactional(readOnly = true)
    public List<CourseDto> getCoursesByFaculty(Long facultyId) {
        return courseRepository.findByFacultyId(facultyId).stream()
                .map(course -> mapToDto(course, null))
                .collect(Collectors.toList());
    }

    @Transactional
    public CourseDto createCourse(CourseDto dto) {
        if (courseRepository.findByCode(dto.getCode()).isPresent()) {
            throw new RuntimeException("Course code already exists: " + dto.getCode());
        }

        Faculty faculty = null;
        if (dto.getFacultyId() != null) {
            faculty = facultyRepository.findById(dto.getFacultyId())
                    .orElseThrow(() -> new RuntimeException("Faculty not found with id: " + dto.getFacultyId()));
        }

        Course course = Course.builder()
                .code(dto.getCode().toUpperCase())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .credits(dto.getCredits() != null ? dto.getCredits() : 3)
                .department(dto.getDepartment())
                .semester(dto.getSemester() != null ? dto.getSemester() : 1)
                .syllabusText(dto.getSyllabusText())
                .faculty(faculty)
                .build();

        Course saved = courseRepository.save(course);
        return mapToDto(saved, null);
    }

    @Transactional
    public CourseDto updateCourse(Long id, CourseDto dto) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));

        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        course.setCredits(dto.getCredits());
        course.setDepartment(dto.getDepartment());
        course.setSemester(dto.getSemester());

        if (dto.getSyllabusText() != null) {
            course.setSyllabusText(dto.getSyllabusText());
        }

        if (dto.getFacultyId() != null) {
            Faculty faculty = facultyRepository.findById(dto.getFacultyId())
                    .orElseThrow(() -> new RuntimeException("Faculty not found with id: " + dto.getFacultyId()));
            course.setFaculty(faculty);
        }

        Course updated = courseRepository.save(course);
        return mapToDto(updated, null);
    }

    @Transactional
    public CourseDto updateSyllabus(Long courseId, String syllabusContent) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));

        course.setSyllabusText(syllabusContent);
        Course updated = courseRepository.save(course);
        return mapToDto(updated, null);
    }

    @Transactional
    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new RuntimeException("Course not found with id: " + id);
        }
        courseRepository.deleteById(id);
    }

    private CourseDto mapToDto(Course course, Long currentStudentId) {
        int enrollmentCount = enrollmentRepository.findByCourseId(course.getId()).size();
        boolean isEnrolled = false;
        if (currentStudentId != null) {
            isEnrolled = enrollmentRepository.existsByStudentIdAndCourseId(currentStudentId, course.getId());
        }

        String facultyName = null;
        String facultyEmail = null;
        Long facultyId = null;

        if (course.getFaculty() != null) {
            facultyId = course.getFaculty().getId();
            facultyName = course.getFaculty().getUser().getFirstName() + " " + course.getFaculty().getUser().getLastName();
            facultyEmail = course.getFaculty().getUser().getEmail();
        }

        return CourseDto.builder()
                .id(course.getId())
                .code(course.getCode())
                .title(course.getTitle())
                .description(course.getDescription())
                .credits(course.getCredits())
                .department(course.getDepartment())
                .semester(course.getSemester())
                .syllabusText(course.getSyllabusText())
                .facultyId(facultyId)
                .facultyName(facultyName)
                .facultyEmail(facultyEmail)
                .createdAt(course.getCreatedAt())
                .enrolledStudentsCount(enrollmentCount)
                .isEnrolled(isEnrolled)
                .build();
    }
}
