package org.quizservice.service;

import org.quizservice.entity.QuestionWrapper;
import org.quizservice.entity.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuizService {
    ResponseEntity<String> createQuiz(String category, int numQ, String title);
    ResponseEntity<List<QuestionWrapper>> getQuizQUestions(Integer id);
    ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses);
}
