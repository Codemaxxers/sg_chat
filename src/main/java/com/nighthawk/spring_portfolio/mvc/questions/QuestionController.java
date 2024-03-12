package com.nighthawk.spring_portfolio.mvc.questions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    //Get Question by Course
    @GetMapping("/randomQuestion/{unit}")
    public Question getRandomQuestionByUnit(@PathVariable String unit) {
        // Fetch a random question from the database based on the unit
        return questionRepository.findRandomQuestionByUnit(unit);
    }

    //Add Question
    @PostMapping("/makeQuestion")
    public Question makeQuestion(@RequestBody Question question) {
        return questionRepository.save(question);
    }
    
    //Delete Question
    @DeleteMapping("/deleteQuestion/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionRepository.deleteById(id);
    }
}
