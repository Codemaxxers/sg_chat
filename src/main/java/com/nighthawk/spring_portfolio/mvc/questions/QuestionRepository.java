package com.nighthawk.spring_portfolio.mvc.questions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(nativeQuery = true, name = "Question.findByCourse")
    List<Question> findByCourse(String courseName);

    Question findRandomQuestionByUnit(String unit);
}