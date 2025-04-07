package com.quiz.quiz_app.controller;

import com.quiz.quiz_app.dto.QuizRequestDTO;
import com.quiz.quiz_app.dto.QuizResponseDTO;
import com.quiz.quiz_app.entity.Category;
import com.quiz.quiz_app.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    // ✅ 퀴즈 등록 (관리자용)
    @PostMapping
    public ResponseEntity<Void> createQuiz(@RequestBody QuizRequestDTO request) {
        quizService.createQuiz(request);
        return ResponseEntity.ok().build();
    }

    // ✅ 카테고리별 퀴즈 랜덤 조회
    @GetMapping("/random")
    public ResponseEntity<List<QuizResponseDTO>> getRandomQuizzes(
            @RequestParam Category category,
            @RequestParam(defaultValue = "10") int count) {
        List<QuizResponseDTO> quizzes = quizService.getRandomQuizzesByCategory(category, count);
        return ResponseEntity.ok(quizzes);
    }

    // ✅ 단일 퀴즈 조회
    @GetMapping("/{id}")
    public ResponseEntity<QuizResponseDTO> getQuizById(@PathVariable Long id) {
        QuizResponseDTO quiz = quizService.getQuizById(id);
        return ResponseEntity.ok(quiz);
    }

    // ✅ 카테고리 목록 조회
    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCategories() {
        List<String> categories = Arrays.stream(Category.values())
                .map(Enum::name)
                .toList();
        return ResponseEntity.ok(categories);
    }
}
