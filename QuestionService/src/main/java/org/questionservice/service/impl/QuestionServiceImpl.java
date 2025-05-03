package org.questionservice.service.impl;


import org.questionservice.entity.Question;
import org.questionservice.entity.QuestionWrapper;
import org.questionservice.entity.Response;
import org.questionservice.repo.QuestionRepo;
import org.questionservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepo questionRepo;

    @Override
    public ResponseEntity<List<Question>> getAllQuestion() {
        try {
            List<Question> questions = questionRepo.findAll();
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Question>> getAllQuestionByCategory(String category) {
        List<Question> questions = questionRepo.findAllByCategoryIgnoreCase(category);
        try {
            if (questions.isEmpty()){
                return new ResponseEntity<>(questions, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> addQuestion(Question question) {
        questionRepo.save(question);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> deleteQuestion(Integer id) {
        questionRepo.deleteById(id);
        return new ResponseEntity<>("successfully deleted", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Integer>> getQuestionForQuiz(String categoryName, Integer numQuestions) {
        List<Integer> questions = questionRepo.findRandomQUestionsByCategory(categoryName, numQuestions);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Optional<Question>> questions = new ArrayList<>();
        for(Integer id: questionIds){
            questions.add(questionRepo.findById(id));
        }

        for (Optional<Question> question : questions){
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.get().getId());
            wrapper.setQuestionText(question.get().getQuestionText());
            wrapper.setOption1(question.get().getOption1());
            wrapper.setOption2(question.get().getOption2());
            wrapper.setOption3(question.get().getOption3());
            wrapper.setOption4(question.get().getOption4());
            wrappers.add(wrapper);
        }
        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int right = 0;
        for (Response response : responses){
            Optional<Question> question = questionRepo.findById(response.getId());
            if (response.getResponse().equals(question.get().getRightAnswer())){
                right++;
            }
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
