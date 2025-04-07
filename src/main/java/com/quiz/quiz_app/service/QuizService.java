package com.quiz.quiz_app.service;

import com.quiz.quiz_app.dto.QuizRequestDTO;
import com.quiz.quiz_app.dto.QuizResponseDTO;
import com.quiz.quiz_app.entity.Category;
import com.quiz.quiz_app.entity.Quiz;
import com.quiz.quiz_app.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;

    /**
     * [퀴즈 등록]
     * - 클라이언트에서 받은 DTO를 Entity로 변환하여 DB에 저장
     * - 정답 및 카테고리 정보도 함께 저장됨
     */
    public void createQuiz(QuizRequestDTO request) {
        Quiz quiz = request.toEntity(); // DTO → Entity 변환
        quizRepository.save(quiz);      // DB 저장
    }

    /**
     * [카테고리별 퀴즈 랜덤 조회]
     * - 특정 카테고리에서 퀴즈를 모두 조회한 뒤
     * - 무작위로 섞고(count만큼 잘라서) 반환
     */
    public List<QuizResponseDTO> getRandomQuizzesByCategory(Category category, int count) {
        List<Quiz> quizzes = quizRepository.findByCategory(category); // 카테고리 기반 전체 퀴즈 조회
        Collections.shuffle(quizzes); // 순서 랜덤 섞기
        return quizzes.stream()
                .limit(count) // 최대 count개만 추출
                .map(QuizResponseDTO::from) // Entity → DTO 변환
                .collect(Collectors.toList());
    }

    /**
     * [단일 퀴즈 조회]
     * - ID로 퀴즈 하나 조회
     * - 없으면 예외 발생
     */
    public QuizResponseDTO getQuizById(Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 퀴즈가 없습니다. id=" + id));
        return QuizResponseDTO.from(quiz);
    }
}
