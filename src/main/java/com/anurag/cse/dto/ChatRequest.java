package com.anurag.cse.dto;

import jakarta.validation.constraints.NotBlank;

public class ChatRequest {
    @NotBlank(message = "Message cannot be empty")
    private String message;

    private String conversationId;
    private String studentDepartment;
    private Integer studentSemester;

    public ChatRequest() {}

    public ChatRequest(String message, String conversationId, String studentDepartment, Integer studentSemester) {
        this.message = message;
        this.conversationId = conversationId;
        this.studentDepartment = studentDepartment;
        this.studentSemester = studentSemester;
    }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getConversationId() { return conversationId; }
    public void setConversationId(String conversationId) { this.conversationId = conversationId; }

    public String getStudentDepartment() { return studentDepartment; }
    public void setStudentDepartment(String studentDepartment) { this.studentDepartment = studentDepartment; }

    public Integer getStudentSemester() { return studentSemester; }
    public void setStudentSemester(Integer studentSemester) { this.studentSemester = studentSemester; }
}
