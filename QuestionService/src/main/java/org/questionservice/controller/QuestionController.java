package org.questionservice.controller;

import org.questionservice.entity.Question;
import org.questionservice.entity.QuestionWrapper;
import org.questionservice.entity.Response;
import org.questionservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getALlQuestion(){
        return questionService.getAllQuestion();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>>getQuestionByCategory(@PathVariable String category){
        return questionService.getAllQuestionByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestionById(@PathVariable Integer id){
        return questionService.deleteQuestion(id);
    }

    //generating quiz
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions){
        return questionService.getQuestionForQuiz(categoryName, numQuestions);
    }

    //getQuestion(questionID)
    @GetMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>>getQuestionFromId(@RequestParam("id") List<Integer> questionIds){
        return questionService.getQuestionFromId(questionIds);
    }

    //getScore
    @PostMapping("calculateScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }
}
