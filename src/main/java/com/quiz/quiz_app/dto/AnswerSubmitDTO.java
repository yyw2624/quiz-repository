package com.quiz.quiz_app.dto;

import com.quiz.quiz_app.entity.Answer;
import com.quiz.quiz_app.entity.AnswerType;
import lombok.Getter;

@Getter
public class AnswerSubmitDTO {
    // 유저가 퀴즈 정답 제출 시, 한 문제에 대해 보내는 데이터
    private Long quizId; // 퀴즈 ID
    private AnswerType userAnswer; // 유저가 고른 답

    // AnswerSubmitDTO 값을 Entity로 변환 후 Answer DB에 저장하기 위한 작업
    public Answer toEntity(){
        return Answer.builder()
                .userAnswer(userAnswer)
                .build();
    }

}
