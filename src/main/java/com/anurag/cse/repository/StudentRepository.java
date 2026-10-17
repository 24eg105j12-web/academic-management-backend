package com.anurag.cse.repository;

import com.anurag.cse.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE s.user.id = :userId")
    Optional<Student> findByUserId(@Param("userId") Long userId);

    Optional<Student> findByStudentId(String studentId);

    @Query("SELECT s FROM Student s WHERE s.user.username = :username")
    Optional<Student> findByUserUsername(@Param("username") String username);
}
