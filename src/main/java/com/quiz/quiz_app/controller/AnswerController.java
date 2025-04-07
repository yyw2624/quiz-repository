package com.quiz.quiz_app.controller;

import com.quiz.quiz_app.dto.AnswerDTO;
import com.quiz.quiz_app.dto.AnswerResultDTO;
import com.quiz.quiz_app.dto.AnswerSubmitDTO;
import com.quiz.quiz_app.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    // ✅ 유저 정답 제출 & 채점
    @PostMapping("/submit")
    public ResponseEntity<AnswerResultDTO> submitAnswers(
            @RequestBody List<AnswerSubmitDTO> answers,
            @RequestParam Long userId) {
        AnswerResultDTO result = answerService.submitAnswers(userId, answers);
        return ResponseEntity.ok(result);
    }

    // ✅ 유저 풀이 기록 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AnswerDTO>> getAnswersByUser(@PathVariable Long userId) {
        List<AnswerDTO> answers = answerService.getAnswersByUser(userId);
        return ResponseEntity.ok(answers);
    }

    // ✅ 특정 퀴즈의 정답 기록 조회
    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<AnswerDTO>> getAnswersByQuiz(@PathVariable Long quizId) {
        List<AnswerDTO> answers = answerService.getAnswersByQuiz(quizId);
        return ResponseEntity.ok(answers);
    }
}
