package org.quizservice.feign;

import org.quizservice.entity.QuestionWrapper;
import org.quizservice.entity.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    //generating quiz
    @GetMapping("question/generate")
    ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions);

    //getQuestion(questionID)
    @GetMapping("question/getQuestions")
    ResponseEntity<List<QuestionWrapper>>getQuestionFromId(@RequestParam("id") List<Integer> questionIds);

    //getScore
    @PostMapping("question/calculateScore")
    ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);

}
