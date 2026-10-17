-- Baseline Seed Data for Roles
INSERT IGNORE INTO roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT IGNORE INTO roles (id, name) VALUES (2, 'ROLE_FACULTY');
INSERT IGNORE INTO roles (id, name) VALUES (3, 'ROLE_STUDENT');

-- Campus Knowledge Base for RAG Advisor
INSERT IGNORE INTO knowledge_documents (id, title, category, content, tags) VALUES
(1, 'Course Add/Drop & Registration Policy', 'POLICY', 'Students can register or drop courses during the first two weeks of the semester without penalty. After week 2 and up to week 6, withdrawals receive a W grade on the transcript. Maximum credit load is 21 credits per semester.', 'registration,add,drop,credits'),
(2, 'Attendance & Minimum Requirement', 'POLICY', 'A minimum of 75% attendance is strictly required in all enrolled courses to be eligible for mid-term and end-semester examinations. Medical exemptions require institutional health center verification within 3 business days.', 'attendance,exams,medical'),
(3, 'Grading Scale & CGPA Calculation', 'ACADEMIC', 'Grading uses a 10-point scale: A+ (90-100%, 10.0), A (80-89%, 9.0), B+ (70-79%, 8.0), B (60-69%, 7.0), C (50-59%, 6.0), D (40-49%, 5.0), and F (<40%, 0.0). CGPA is computed as sum of (credits * grade points) divided by total completed credits.', 'grades,cgpa,scale'),
(4, 'Mid-Term and Final Examination Regulations', 'EXAMS', 'Mid-term exams commence in week 8. Final semester examinations are scheduled in week 16. Hall tickets are generated only for students with clear fee dues and >75% attendance. No electronic devices are allowed in examination halls.', 'exams,schedule,hall ticket,finals'),
(5, 'Computer Science Prerequisite Flow', 'PREREQUISITES', 'CS101 (Intro to CS & Python) is prerequisite for CS201 (Data Structures & Algorithms). CS201 is required for CS301 (Operating Systems) and CS302 (Database Management Systems). Advanced AI electives require CS201 and Linear Algebra.', 'prerequisites,cs101,cs201,cs301');
