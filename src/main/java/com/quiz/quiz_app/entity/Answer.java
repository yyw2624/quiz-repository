package com.quiz.quiz_app.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Entity를 구분하기 위한 id

    private boolean isCorrect; // 정답 여부

    // 사용자가 제출한 답
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AnswerType userAnswer;

    // User를 참조
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Quiz를 참조
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    // Builder 패턴으로 Answer 생성자 생성
    @Builder
    public Answer(boolean isCorrect, AnswerType userAnswer, User user, Quiz quiz){
        this.isCorrect = isCorrect;
        this.userAnswer = userAnswer;
        this.user = user;
        this.quiz = quiz;
    }

}
