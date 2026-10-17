package com.anurag.cse.service;

import com.anurag.cse.dto.UserDto;
import com.anurag.cse.entity.Faculty;
import com.anurag.cse.repository.FacultyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Transactional(readOnly = true)
    public List<UserDto> getAllFaculty() {
        return facultyRepository.findAll().stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDto getFacultyById(Long id) {
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faculty not found with id: " + id));
        return mapToUserDto(faculty);
    }

    @Transactional(readOnly = true)
    public UserDto getFacultyByUsername(String username) {
        Faculty faculty = facultyRepository.findByUserUsername(username)
                .orElseThrow(() -> new RuntimeException("Faculty not found for username: " + username));
        return mapToUserDto(faculty);
    }

    private UserDto mapToUserDto(Faculty faculty) {
        return UserDto.builder()
                .id(faculty.getUser().getId())
                .username(faculty.getUser().getUsername())
                .email(faculty.getUser().getEmail())
                .firstName(faculty.getUser().getFirstName())
                .lastName(faculty.getUser().getLastName())
                .role("ROLE_FACULTY")
                .active(faculty.getUser().getActive())
                .employeeId(faculty.getEmployeeId())
                .department(faculty.getDepartment())
                .createdAt(faculty.getUser().getCreatedAt())
                .build();
    }
}
