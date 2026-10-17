# AcademiQ AI - Academic Management Backend

Production-grade **AI-Powered Academic Management System** built with **Spring Boot 3**, **Java 17**, **Spring Security (JWT)**, **Spring Data JPA**, **OpenAPI / Swagger 3**, and **Spring AI**.

---

## Tech Stack
* **Java:** 17+
* **Framework:** Spring Boot 3.5.x
* **Security:** Spring Security 6 with stateless JWT Bearer token authentication
* **ORM / Database:** Spring Data JPA / Hibernate, MySQL 8.x (with automatic H2 in-memory fallback)
* **API Documentation:** SpringDoc OpenAPI 3 / Swagger UI
* **Build Tool:** Maven

---

## Features
1. **Role-Based Access Control (RBAC):** `ADMIN`, `FACULTY`, `STUDENT`.
2. **Core Academic Management:**
   - Course catalog, syllabus repository, credit hours, department mapping.
   - Student semester enrollment and course registration.
   - Assignment authoring, rubric criteria, and submission workflow.
3. **AI Intelligent Services:**
   - **AI Quiz & Flashcard Generator:** Generates structured MCQ questions and flashcards with explanations.
   - **AI Assignment Grading & Rubric Evaluation:** Evaluates submissions against assignment rubrics with score breakdown and analytical feedback.
   - **Campus RAG / AI Academic Advisor:** Q&A engine citing verified campus academic policies and prerequisite flows.
   - **Resilient Fallback Engine:** Heuristic evaluation engine ensures zero downtime even when external AI API quotas are exceeded.

---

## Getting Started

### 1. Prerequisites
* Java JDK 17 or higher
* Apache Maven 3.8+
* MySQL 8.x (optional — defaults automatically to H2 in-memory if MySQL is offline)

### 2. Configuration
Copy `.env.example` or adjust `src/main/resources/application.properties`:
```properties
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/academic_ai_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root
```

### 3. Build & Run
```bash
mvn clean compile
mvn spring-boot:run
```

The application starts on `http://localhost:8080`.

---

## API Documentation (Swagger UI)
Access the interactive OpenAPI interface at:
* **[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**
* OpenAPI Specification: `http://localhost:8080/v3/api-docs`

---

## Default Seed Credentials
The system automatically seeds demo accounts upon startup:
* **Admin:** `admin` / `admin123`
* **Faculty:** `faculty` / `faculty123`
* **Student:** `student` / `student123`
