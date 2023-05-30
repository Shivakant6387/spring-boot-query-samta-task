package com.example.springbootquerysamtatask.controller;

import com.example.springbootquerysamtatask.dto.AnswerDto;
import com.example.springbootquerysamtatask.dto.QuestionDto;
import com.example.springbootquerysamtatask.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/play")
    public QuestionDto play() {
        return questionService.getRandomQuestion();
    }

    @PostMapping("/next")
    public Map<String, Object> next(@RequestBody AnswerDto answerRequest) {
        return questionService.getNextQuestion(answerRequest);
    }
}
