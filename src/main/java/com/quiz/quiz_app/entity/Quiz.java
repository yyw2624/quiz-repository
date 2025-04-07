package com.quiz.quiz_app.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Entity를 구분하기 위한 id

    @Column(nullable = false)
    private String content;

    // DB에 Category enum 파일을 문자열로 저장하고 정의하기 위함
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    // DB에 AnswerType enum 파일을 문자열로 저장하고 정의하기 위함
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AnswerType correctAnswer;

    // Builder 패턴으로 Quiz 생성자 생성
    @Builder
    public Quiz(String content, Category category, AnswerType correctAnswer) {
        this.content = content;
        this.category = category;
        this.correctAnswer = correctAnswer;
    }

}
