package com.quiz.quiz_app.service;

import com.quiz.quiz_app.dto.AnswerDTO;
import com.quiz.quiz_app.dto.AnswerResultDTO;
import com.quiz.quiz_app.dto.AnswerSubmitDTO;
import com.quiz.quiz_app.entity.Answer;
import com.quiz.quiz_app.entity.Quiz;
import com.quiz.quiz_app.entity.User;
import com.quiz.quiz_app.repository.AnswerRepository;
import com.quiz.quiz_app.repository.QuizRepository;
import com.quiz.quiz_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;

    /**
     * [정답 제출 및 채점]
     * - 유저 ID로 유저 조회
     * - 제출된 답변 리스트를 순회하며 각각 채점
     * - 정답 여부를 기반으로 점수를 계산하고, AnswerResultDTO 반환
     */
    public AnswerResultDTO submitAnswers(Long userId, List<AnswerSubmitDTO> submitList) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        List<Answer> answers = submitList.stream().map(submit -> {
            Quiz quiz = quizRepository.findById(submit.getQuizId())
                    .orElseThrow(() -> new IllegalArgumentException("해당 퀴즈가 없습니다."));

            boolean isCorrect = quiz.getCorrectAnswer().equals(submit.getUserAnswer());

            return Answer.builder()
                    .quiz(quiz)
                    .user(user)
                    .userAnswer(submit.getUserAnswer())
                    .isCorrect(isCorrect)
                    .build();
        }).collect(Collectors.toList());

        answerRepository.saveAll(answers); // DB에 한꺼번에 저장

        return AnswerResultDTO.from(answers); // 점수 계산 + DTO 변환
    }

    /**
     * [유저별 풀이 기록 조회]
     */
    public List<AnswerDTO> getAnswersByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
        return answerRepository.findByUser(user).stream()
                .map(AnswerDTO::from)
                .collect(Collectors.toList());
    }

    /**
     * [퀴즈별 정답 기록 조회]
     */
    public List<AnswerDTO> getAnswersByQuiz(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new IllegalArgumentException("퀴즈가 존재하지 않습니다."));
        return answerRepository.findByQuiz(quiz).stream()
                .map(AnswerDTO::from)
                .collect(Collectors.toList());
    }
}
