package com.quiz.quiz_app.repository;

import com.quiz.quiz_app.entity.Category;
import com.quiz.quiz_app.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    /**
     * 특정 카테고리에 해당하는 모든 퀴즈 목록을 반환
     */
    List<Quiz> findByCategory(Category category);
}
