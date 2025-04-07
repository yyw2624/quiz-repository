package com.quiz.quiz_app.repository;

import com.quiz.quiz_app.entity.Answer;
import com.quiz.quiz_app.entity.Quiz;
import com.quiz.quiz_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    /**
     * 특정 유저가 푼 모든 답변 조회
     */
    List<Answer> findByUser(User user);

    /**
     * 특정 퀴즈에 대한 모든 사용자 답변 조회
     */
    List<Answer> findByQuiz(Quiz quiz);
}
