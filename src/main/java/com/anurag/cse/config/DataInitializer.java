package com.anurag.cse.config;

import com.anurag.cse.entity.*;
import com.anurag.cse.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final AssignmentRepository assignmentRepository;
    private final SubmissionRepository submissionRepository;
    private final KnowledgeDocumentRepository knowledgeDocumentRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(RoleRepository roleRepository,
                           UserRepository userRepository,
                           StudentRepository studentRepository,
                           FacultyRepository facultyRepository,
                           CourseRepository courseRepository,
                           EnrollmentRepository enrollmentRepository,
                           AssignmentRepository assignmentRepository,
                           SubmissionRepository submissionRepository,
                           KnowledgeDocumentRepository knowledgeDocumentRepository,
                           PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.assignmentRepository = assignmentRepository;
        this.submissionRepository = submissionRepository;
        this.knowledgeDocumentRepository = knowledgeDocumentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) {
        log.info("Checking and seeding academic management initial dataset...");

        // 1. Roles
        Role adminRole = roleRepository.findByName(RoleType.ROLE_ADMIN)
                .orElseGet(() -> roleRepository.save(new Role(RoleType.ROLE_ADMIN)));
        Role facultyRole = roleRepository.findByName(RoleType.ROLE_FACULTY)
                .orElseGet(() -> roleRepository.save(new Role(RoleType.ROLE_FACULTY)));
        Role studentRole = roleRepository.findByName(RoleType.ROLE_STUDENT)
                .orElseGet(() -> roleRepository.save(new Role(RoleType.ROLE_STUDENT)));

        // 2. Admin User
        if (!userRepository.existsByUsername("admin")) {
            User admin = User.builder()
                    .username("admin")
                    .email("admin@academic.edu")
                    .password(passwordEncoder.encode("admin123"))
                    .firstName("System")
                    .lastName("Administrator")
                    .role(adminRole)
                    .active(true)
                    .build();
            userRepository.save(admin);
            log.info("Created default admin user: admin / admin123");
        }

        // 3. Faculty User
        Faculty turingFaculty = null;
        if (!userRepository.existsByUsername("faculty")) {
            User facultyUser = User.builder()
                    .username("faculty")
                    .email("faculty@academic.edu")
                    .password(passwordEncoder.encode("faculty123"))
                    .firstName("Alan")
                    .lastName("Turing")
                    .role(facultyRole)
                    .active(true)
                    .build();
            User savedFacultyUser = userRepository.save(facultyUser);

            turingFaculty = Faculty.builder()
                    .user(savedFacultyUser)
                    .employeeId("FAC-1001")
                    .department("Computer Science & Engineering")
                    .designation("Professor & Department Chair")
                    .officeHours("Mon/Wed 2:00 PM - 5:00 PM (Room 402)")
                    .build();
            turingFaculty = facultyRepository.save(turingFaculty);
            log.info("Created default faculty user: faculty / faculty123");
        } else {
            turingFaculty = facultyRepository.findByUserUsername("faculty").orElse(null);
        }

        // 4. Student User (Ada Lovelace)
        Student adaStudent = null;
        if (!userRepository.existsByUsername("student")) {
            User studentUser = User.builder()
                    .username("student")
                    .email("student@academic.edu")
                    .password(passwordEncoder.encode("student123"))
                    .firstName("Ada")
                    .lastName("Lovelace")
                    .role(studentRole)
                    .active(true)
                    .build();
            User savedStudentUser = userRepository.save(studentUser);

            adaStudent = Student.builder()
                    .user(savedStudentUser)
                    .studentId("STU-202401")
                    .department("Computer Science & Engineering")
                    .semester(5)
                    .cgpa(9.45)
                    .phone("+1 555-0199")
                    .build();
            adaStudent = studentRepository.save(adaStudent);
            log.info("Created default student user: student / student123");
        } else {
            adaStudent = studentRepository.findByUserUsername("student").orElse(null);
        }

        // 5. Courses
        Course cs201 = null;
        if (courseRepository.findByCode("CS201").isEmpty()) {
            cs201 = Course.builder()
                    .code("CS201")
                    .title("Data Structures & Algorithms")
                    .description("Core foundational computer science course covering linear and non-linear data structures, balanced search trees, graph algorithms, asymptotic analysis, and dynamic programming.")
                    .credits(4)
                    .department("Computer Science & Engineering")
                    .semester(3)
                    .syllabusText("Module 1: Asymptotic Complexity & Divide-and-Conquer (O, Omega, Theta notations, Master Theorem).\n" +
                            "Module 2: Advanced Trees (Binary Search Trees, AVL balance invariants, Red-Black Trees, B-Trees).\n" +
                            "Module 3: Graph Representations & Algorithms (BFS, DFS, Dijkstra Shortest Path, Bellman-Ford, Kruskal/Prim MST).\n" +
                            "Module 4: Dynamic Programming & Greedy Algorithms (Knapsack, LCS, Matrix Chain Multiplication).\n" +
                            "Module 5: Hashing & Amortized Analysis (Collision resolution, Universal hashing, Disjoint-Set union-find).")
                    .faculty(turingFaculty)
                    .build();
            cs201 = courseRepository.save(cs201);
        } else {
            cs201 = courseRepository.findByCode("CS201").get();
        }

        if (courseRepository.findByCode("CS301").isEmpty()) {
            Course cs301 = Course.builder()
                    .code("CS301")
                    .title("Operating Systems Principles")
                    .description("Detailed exploration of kernel architecture, concurrent process synchronization, scheduling policies, virtual memory management, file systems, and distributed OS primitives.")
                    .credits(4)
                    .department("Computer Science & Engineering")
                    .semester(5)
                    .syllabusText("Module 1: Operating System Architectures, System Calls, and Dual-mode operations.\n" +
                            "Module 2: Process Scheduling, Threads, Semaphores, Mutexes, and Deadlock avoidance (Banker's Algorithm).\n" +
                            "Module 3: Memory Management (Paging, Segmentation, TLB, Page replacement algorithms: LRU, FIFO).\n" +
                            "Module 4: Storage & File Systems (Inode structures, Disk scheduling algorithms: SCAN, C-LOOK).\n" +
                            "Module 5: Security, Virtualization, and Containerization fundamentals.")
                    .faculty(turingFaculty)
                    .build();
            courseRepository.save(cs301);
        }

        if (courseRepository.findByCode("AI401").isEmpty()) {
            Course ai401 = Course.builder()
                    .code("AI401")
                    .title("Artificial Intelligence & Machine Learning")
                    .description("Introduction to state-space search, heuristic game playing, supervised learning, gradient descent, backpropagation, and transformer architectures.")
                    .credits(4)
                    .department("Computer Science & Engineering")
                    .semester(7)
                    .syllabusText("Module 1: Problem Formulation & Informed Search (A* search, Heuristics, Alpha-Beta pruning).\n" +
                            "Module 2: Supervised Learning (Linear Regression, Logistic Regression, Support Vector Machines).\n" +
                            "Module 3: Deep Neural Networks (Multilayer perceptrons, Backpropagation, Activation functions).\n" +
                            "Module 4: Sequence Models & Attention (RNNs, Transformers, Self-Attention mechanisms).\n" +
                            "Module 5: Ethics, Alignment, and Prompt Engineering in Generative AI.")
                    .faculty(turingFaculty)
                    .build();
            courseRepository.save(ai401);
        }

        // 6. Enrollments
        if (adaStudent != null && cs201 != null) {
            if (!enrollmentRepository.existsByStudentIdAndCourseId(adaStudent.getId(), cs201.getId())) {
                Enrollment enrollment = Enrollment.builder()
                        .student(adaStudent)
                        .course(cs201)
                        .grade("A")
                        .status("ACTIVE")
                        .build();
                enrollmentRepository.save(enrollment);
            }
        }

        // 7. Assignments & Submissions
        if (cs201 != null) {
            Assignment avlAssignment = null;
            if (assignmentRepository.findByCourseId(cs201.getId()).isEmpty()) {
                avlAssignment = Assignment.builder()
                        .course(cs201)
                        .title("Self-Balancing Trees: AVL Rotations & Complexity Verification")
                        .description("Implement an AVL tree with Left-Left, Right-Right, Left-Right, and Right-Left rebalancing rotations. Document the worst-case proof showing height is strictly O(log n). Provide unit test cases verifying tree depth invariant after 1,000 random insertions.")
                        .rubric("1. Mathematical Rigor: Proof of height upper bound (25%).\n" +
                                "2. Algorithm Implementation: Accuracy of four rotation routines (35%).\n" +
                                "3. Performance Verification: Test suite validating O(log n) tree depth (25%).\n" +
                                "4. Code Style & Documentation: Clean naming and comments (15%).")
                        .maxScore(100)
                        .dueDate(LocalDateTime.now().plusDays(7))
                        .build();
                avlAssignment = assignmentRepository.save(avlAssignment);
            } else {
                avlAssignment = assignmentRepository.findByCourseId(cs201.getId()).get(0);
            }

            if (adaStudent != null && avlAssignment != null) {
                if (submissionRepository.findByAssignmentIdAndStudentId(avlAssignment.getId(), adaStudent.getId()).isEmpty()) {
                    Submission sub = Submission.builder()
                            .assignment(avlAssignment)
                            .student(adaStudent)
                            .content("## Solution: AVL Balanced Tree Implementation\n\n" +
                                    "### 1. Mathematical Bound\n" +
                                    "Let N(h) be the minimum number of nodes in an AVL tree of height h.\n" +
                                    "N(h) = 1 + N(h-1) + N(h-2), where N(0) = 1, N(1) = 2.\n" +
                                    "This Fibonacci-like relation proves h < 1.44 log2(n+2) - 0.328, strictly guaranteeing O(log n) time for search, insertion, and deletion.\n\n" +
                                    "### 2. Rotations Implementation\n" +
                                    "- Left Rotation (RR Imbalance): Re-parents the right child's left subtree to current node.\n" +
                                    "- Right Rotation (LL Imbalance): Re-parents left child's right subtree to current node.\n" +
                                    "- Double Rotations (LR & RL): Compound single rotations restoring the {-1, 0, +1} balance factor.\n\n" +
                                    "### 3. Empirical Test Suite\n" +
                                    "Simulated 1,000 randomized integers; max tree depth observed was 11, well within theoretical limit ceil(1.44 * log2(1000)) = 15.")
                            .score(95)
                            .aiFeedback("Outstanding submission! Mathematical proof using the Fibonacci lower bound is rigorous and exact. Implementation details for both single and double rotations are accurately described. Empirical depth of 11 for 1,000 keys validates logarithmic bound.")
                            .facultyNotes("Exemplary work by Ada Lovelace. Clear and well formatted.")
                            .status("GRADED")
                            .build();
                    submissionRepository.save(sub);
                }
            }
        }

        // 8. Knowledge Documents for Campus RAG
        if (knowledgeDocumentRepository.count() == 0) {
            knowledgeDocumentRepository.save(KnowledgeDocument.builder()
                    .title("Course Add/Drop & Registration Deadlines")
                    .category("ACADEMIC_POLICY")
                    .content("Students can add or drop courses without penalty during Weeks 1 and 2 of the academic semester. " +
                            "Withdrawals between Week 3 and Week 6 result in an official 'W' grade on the academic transcript. " +
                            "No course drops are permitted after Week 6 except for verified medical hardship approved by the Dean.")
                    .tags("registration,add,drop,withdrawal,deadline")
                    .build());

            knowledgeDocumentRepository.save(KnowledgeDocument.builder()
                    .title("Attendance Policy and Examination Eligibility")
                    .category("ATTENDANCE")
                    .content("The University mandates a strict minimum of 75% attendance in all lecture, tutorial, and laboratory sessions. " +
                            "Students falling between 65% and 74% may petition with certified medical documentation submitted within 3 days. " +
                            "Students with attendance below 65% are categorically barred from sitting for final semester examinations and receive an 'FA' grade.")
                    .tags("attendance,minimum,eligibility,medical,shortage")
                    .build());

            knowledgeDocumentRepository.save(KnowledgeDocument.builder()
                    .title("Degree Grading Scale & CGPA Formula")
                    .category("EXAMINATIONS")
                    .content("Grading is based on a 10-point Scale:\n" +
                            "• A+ (90-100%): 10.0 Grade Points (Outstanding)\n" +
                            "• A  (80-89%):  9.0 Grade Points (Excellent)\n" +
                            "• B+ (70-79%):  8.0 Grade Points (Very Good)\n" +
                            "• B  (60-69%):  7.0 Grade Points (Good)\n" +
                            "• C  (50-59%):  6.0 Grade Points (Satisfactory)\n" +
                            "• D  (40-49%):  5.0 Grade Points (Pass)\n" +
                            "• F  (<40%):    0.0 Grade Points (Fail)\n" +
                            "Semester SGPA and cumulative CGPA are weighted averages: SUM(Credits * Grade Points) / SUM(Credits).")
                    .tags("grading,gpa,cgpa,sgpa,scale,formula")
                    .build());

            knowledgeDocumentRepository.save(KnowledgeDocument.builder()
                    .title("Computer Science Course Prerequisites Flow")
                    .category("PREREQUISITES")
                    .content("Course progression rules in Computer Science & Engineering:\n" +
                            "1. CS101 (Intro to CS & Python) is prerequisite for CS201 (Data Structures).\n" +
                            "2. CS201 is strictly required before registering for CS301 (Operating Systems) and CS302 (DBMS).\n" +
                            "3. CS301 and CS302 are prerequisites for CS401 (Distributed Systems) and CS402 (Cloud Computing).\n" +
                            "4. AI401 requires CS201 and Discrete Mathematics.")
                    .tags("prerequisites,cs101,cs201,cs301,ai401,flow")
                    .build());

            log.info("Campus Knowledge Base loaded with 4 official institutional documents.");
        }

        log.info("Academic Management System dataset successfully initialized!");
    }
}
