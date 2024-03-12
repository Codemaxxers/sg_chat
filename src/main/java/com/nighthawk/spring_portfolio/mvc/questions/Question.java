package com.nighthawk.spring_portfolio.mvc.questions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedNativeQuery(name = "Question.findByCourse", query = "SELECT * FROM ?1", resultClass = Question.class)
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Question", nullable = false)
    private String question;

    @Column(name = "Answer 1", nullable = false)
    private String answer1;

    @Column(name = "Answer 2", nullable = false)
    private String answer2;

    @Column(name = "Answer 3", nullable = false)
    private String answer3;

    @Column(name = "Answer 4", nullable = false)
    private String answer4;

    @Column(name = "Correct Answer", nullable = false)
    private Integer correctAnswer;

    @Column(name = "Difficulty", nullable = false)
    private Integer difficulty;

    @Column(name = "Unit", nullable = false)
    private String unit;

    @Column(name = "Points", nullable = false)
    private Integer points;

    // Getters and setters
    public Question(String question, String answer1, String answer2, String answer3, String answer4, Integer correctAnswer, Integer difficulty, String unit, Integer points) {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correctAnswer = correctAnswer;
        this.difficulty = difficulty;
        this.unit = unit;
        this.points = points;
    }

    public static Question[] init() {
        return new Question[] {
            new Question("What is a example of Binary Data?", "777777", "202202", "101010", "521235", 3, 1, "csp", 45),
        };
    }

    public static void main(String[] args) {
        Question questions[] = init();
        for (Question question : questions) {
            System.out.println(question.getQuestion());
        }
    }
}   
