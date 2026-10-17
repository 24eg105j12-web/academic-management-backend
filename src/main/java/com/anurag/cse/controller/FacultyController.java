package com.anurag.cse.controller;

import com.anurag.cse.dto.ApiResponse;
import com.anurag.cse.dto.UserDto;
import com.anurag.cse.service.FacultyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllFaculty() {
        List<UserDto> faculties = facultyService.getAllFaculty();
        return ResponseEntity.ok(ApiResponse.success(faculties));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> getFacultyById(@PathVariable Long id) {
        UserDto faculty = facultyService.getFacultyById(id);
        return ResponseEntity.ok(ApiResponse.success(faculty));
    }
}
