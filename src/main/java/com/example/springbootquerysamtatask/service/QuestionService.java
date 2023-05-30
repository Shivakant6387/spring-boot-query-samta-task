package com.example.springbootquerysamtatask.service;

import com.example.springbootquerysamtatask.dto.AnswerDto;
import com.example.springbootquerysamtatask.dto.QuestionDto;
import com.example.springbootquerysamtatask.model.Question;
import com.example.springbootquerysamtatask.repository.QuestionRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public QuestionDto getRandomQuestion() {
        List<Question> questions = questionRepository.findAll();
        int randomIndex = new Random().nextInt(questions.size());
        Question randomQuestion = questions.get(randomIndex);

        QuestionDto questionDTO = new QuestionDto();
        questionDTO.setQuestionId(randomQuestion.getId());
        questionDTO.setQuestion(randomQuestion.getQuestion());

        return questionDTO;
    }

    public Map<String, Object> getNextQuestion(AnswerDto answerRequest) {
        Optional<Question> questionOptional = questionRepository.findById(answerRequest.getQuestionId());
        if (questionOptional.isEmpty()) {
            throw new IllegalArgumentException("Invalid question ID");
        }

        Question question = questionOptional.get();
        Map<String, Object> response = new HashMap<>();
        response.put("correct_answer", question.getAnswer());
        response.put("next_question", getRandomQuestion());

        return response;
    }
}
