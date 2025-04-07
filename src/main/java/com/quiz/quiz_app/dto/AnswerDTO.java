package com.quiz.quiz_app.dto;

import com.quiz.quiz_app.entity.Answer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AnswerDTO {

    private Long quizId;             // 퀴즈 ID
    private String question;         // 문제 내용
    private String selectedAnswer;   // 유저가 고른 답 (문자열 O/X)
    private boolean isCorrect;       // 정답 여부

    @Builder
    public AnswerDTO(Long quizId, String question, String selectedAnswer, boolean isCorrect) {
        this.quizId = quizId;
        this.question = question;
        this.selectedAnswer = selectedAnswer;
        this.isCorrect = isCorrect;
    }

    /**
     * Entity → DTO 변환 (Answer 기준)
     */
    public static AnswerDTO fromEntity(Answer answer) {
        return AnswerDTO.builder()
                .quizId(answer.getQuiz().getId())
                .question(answer.getQuiz().getContent())
                .selectedAnswer(answer.getUserAnswer().name())
                .isCorrect(answer.isCorrect())
                .build();
    }
}
