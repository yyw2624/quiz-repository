package com.quiz.quiz_app.dto;

import com.quiz.quiz_app.entity.AnswerType;
import com.quiz.quiz_app.entity.Category;
import com.quiz.quiz_app.entity.Quiz;
import lombok.Builder;
import lombok.Getter;

@Getter
public class QuizRequestDTO {
    // 퀴즈 등록 시 클라이언트가 서버로 보내는 데이터
    private String content; // 문제 내용
    private Category category; // 카테고리
    private AnswerType correctAnswer; // 정답

    // QuizRequestDTO 값을 Entity로 변환 후 Quiz DB에 저장하기 위한 작업
    @Builder
    public Quiz toEntity(){
        return Quiz.builder()
                .content(content)
                .category(category)
                .correctAnswer(correctAnswer)
                .build();
    }

}
