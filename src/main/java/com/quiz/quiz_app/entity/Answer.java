package com.quiz.quiz_app.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean correct;

    // 사용자가 제출한 답
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AnswerType userAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @Builder
    public Answer(boolean correct, AnswerType userAnswer, User user, Quiz quiz){
        this.correct = correct;
        this.userAnswer = userAnswer;
        this.user = user;
        this.quiz = quiz;
    }

}
