package org.questionservice.service;

import org.questionservice.entity.Question;
import org.questionservice.entity.QuestionWrapper;
import org.questionservice.entity.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionService {
    ResponseEntity<List<Question>> getAllQuestion();
    ResponseEntity<List<Question>> getAllQuestionByCategory(String category);
    ResponseEntity<String> addQuestion(Question question);
    ResponseEntity<String> deleteQuestion(Integer id);

    ResponseEntity<List<Integer>> getQuestionForQuiz(String categoryName, Integer numQuestions);

    ResponseEntity<List<QuestionWrapper>> getQuestionFromId(List<Integer> questionIds);

    ResponseEntity<Integer> getScore(List<Response> responses);
}
