package com.anurag.cse.controller;

import com.anurag.cse.dto.*;
import com.anurag.cse.service.AiService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/quiz/generate")
    public ResponseEntity<ApiResponse<QuizResponse>> generateQuiz(@Valid @RequestBody QuizRequest request) {
        QuizResponse response = aiService.generateQuiz(request);
        return ResponseEntity.ok(ApiResponse.success(response, "Quiz generated successfully"));
    }

    @PostMapping("/grade/evaluate")
    public ResponseEntity<ApiResponse<GradingResponse>> evaluateAssignment(@Valid @RequestBody GradingRequest request) {
        GradingResponse response = aiService.evaluateAssignment(request);
        return ResponseEntity.ok(ApiResponse.success(response, "Evaluation generated successfully"));
    }

    @PostMapping("/advisor/chat")
    public ResponseEntity<ApiResponse<ChatResponse>> chatAdvisor(@Valid @RequestBody ChatRequest request) {
        ChatResponse response = aiService.chatAdvisor(request);
        return ResponseEntity.ok(ApiResponse.success(response, "Campus AI Advisor response ready"));
    }
}
