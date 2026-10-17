package com.anurag.cse.service;

import com.anurag.cse.dto.*;
import com.anurag.cse.entity.*;
import com.anurag.cse.repository.*;
import com.anurag.cse.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    public AuthService(AuthenticationManager authenticationManager,
                       UserRepository userRepository,
                       RoleRepository roleRepository,
                       StudentRepository studentRepository,
                       FacultyRepository facultyRepository,
                       PasswordEncoder passwordEncoder,
                       JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    public AuthResponse authenticateUser(AuthRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);

        User user = userRepository.findByUsername(loginRequest.getUsername())
                .or(() -> userRepository.findByEmail(loginRequest.getUsername()))
                .orElseThrow(() -> new RuntimeException("User not found"));

        Long studentId = null;
        Long facultyId = null;

        if (user.getRole().getName() == RoleType.ROLE_STUDENT) {
            studentId = studentRepository.findByUserId(user.getId())
                    .map(Student::getId)
                    .orElse(null);
        } else if (user.getRole().getName() == RoleType.ROLE_FACULTY) {
            facultyId = facultyRepository.findByUserId(user.getId())
                    .map(Faculty::getId)
                    .orElse(null);
        }

        return AuthResponse.builder()
                .token(jwt)
                .type("Bearer")
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole().getName().name())
                .studentId(studentId)
                .facultyId(facultyId)
                .build();
    }

    @Transactional
    public ApiResponse<UserDto> registerUser(RegisterRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new RuntimeException("Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("Email address is already in use!");
        }

        RoleType roleType;
        String requestedRole = signUpRequest.getRole().toUpperCase().trim();
        if (!requestedRole.startsWith("ROLE_")) {
            requestedRole = "ROLE_" + requestedRole;
        }

        try {
            roleType = RoleType.valueOf(requestedRole);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role specified. Supported: ADMIN, FACULTY, STUDENT");
        }

        Role userRole = roleRepository.findByName(roleType)
                .orElseGet(() -> roleRepository.save(new Role(roleType)));

        User user = User.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .role(userRole)
                .active(true)
                .build();

        User savedUser = userRepository.save(user);

        // Scaffold role-specific profile
        if (roleType == RoleType.ROLE_STUDENT) {
            String studentCode = signUpRequest.getStudentIdNumber() != null ?
                    signUpRequest.getStudentIdNumber() : "STU-" + System.currentTimeMillis() % 100000;
            String dept = signUpRequest.getDepartment() != null ? signUpRequest.getDepartment() : "Computer Science";
            int sem = signUpRequest.getSemester() != null ? signUpRequest.getSemester() : 1;

            Student student = Student.builder()
                    .user(savedUser)
                    .studentId(studentCode)
                    .department(dept)
                    .semester(sem)
                    .phone(signUpRequest.getPhone())
                    .cgpa(0.0)
                    .build();
            studentRepository.save(student);
        } else if (roleType == RoleType.ROLE_FACULTY) {
            String empCode = signUpRequest.getEmployeeIdNumber() != null ?
                    signUpRequest.getEmployeeIdNumber() : "FAC-" + System.currentTimeMillis() % 100000;
            String dept = signUpRequest.getDepartment() != null ? signUpRequest.getDepartment() : "Computer Science";
            String designation = signUpRequest.getDesignation() != null ? signUpRequest.getDesignation() : "Assistant Professor";

            Faculty faculty = Faculty.builder()
                    .user(savedUser)
                    .employeeId(empCode)
                    .department(dept)
                    .designation(designation)
                    .officeHours(signUpRequest.getOfficeHours() != null ? signUpRequest.getOfficeHours() : "Mon-Fri 2:00 PM - 4:00 PM")
                    .build();
            facultyRepository.save(faculty);
        }

        UserDto userDto = UserDto.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .role(savedUser.getRole().getName().name())
                .active(savedUser.getActive())
                .createdAt(savedUser.getCreatedAt())
                .build();

        return ApiResponse.success(userDto, "User registered successfully");
    }

    @Transactional(readOnly = true)
    public UserDto getCurrentUserDto(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        UserDto.UserDtoBuilder builder = UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole().getName().name())
                .active(user.getActive())
                .createdAt(user.getCreatedAt());

        if (user.getRole().getName() == RoleType.ROLE_STUDENT) {
            studentRepository.findByUserId(user.getId()).ifPresent(student -> {
                builder.studentId(student.getStudentId());
                builder.department(student.getDepartment());
                builder.semester(student.getSemester());
                builder.cgpa(student.getCgpa());
            });
        } else if (user.getRole().getName() == RoleType.ROLE_FACULTY) {
            facultyRepository.findByUserId(user.getId()).ifPresent(faculty -> {
                builder.employeeId(faculty.getEmployeeId());
                builder.department(faculty.getDepartment());
            });
        }

        return builder.build();
    }
}
