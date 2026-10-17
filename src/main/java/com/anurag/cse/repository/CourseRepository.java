package com.anurag.cse.repository;

import com.anurag.cse.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByCode(String code);
    List<Course> findByFacultyId(Long facultyId);
    List<Course> findByDepartment(String department);
    List<Course> findBySemester(Integer semester);
}
