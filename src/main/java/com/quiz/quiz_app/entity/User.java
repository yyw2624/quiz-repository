package com.quiz.quiz_app.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Entity를 구분하기 위한 id

    @Column(nullable = false, unique = true)
    private String username; // 닉네임

    private int score = 0; // 점수의 초기값 0으로 설정

    // Builder 패턴으로 User 생성자 생성
    @Builder
    public User(String username, int score){
        this.username = username;
        this.score = score;
    }

}
