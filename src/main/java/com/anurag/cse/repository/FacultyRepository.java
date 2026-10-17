package com.anurag.cse.repository;

import com.anurag.cse.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    @Query("SELECT f FROM Faculty f WHERE f.user.id = :userId")
    Optional<Faculty> findByUserId(@Param("userId") Long userId);

    Optional<Faculty> findByEmployeeId(String employeeId);

    @Query("SELECT f FROM Faculty f WHERE f.user.username = :username")
    Optional<Faculty> findByUserUsername(@Param("username") String username);
}
