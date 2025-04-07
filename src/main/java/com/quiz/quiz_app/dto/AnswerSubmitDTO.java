package com.quiz.quiz_app.dto;

import com.quiz.quiz_app.entity.Answer;
import com.quiz.quiz_app.entity.AnswerType;
import lombok.Getter;

@Getter
public class AnswerSubmitDTO {
    // 유저가 퀴즈 정답 제출 시, 한 문제에 대해 보내는 데이터
    private Long quizId; // 퀴즈 ID
    private AnswerType userAnswer; // 유저가 고른 답



}
