package org.quizservice.DTO;

import lombok.Data;

@Data
public class QuizDTO {
    String categoryName;
    Integer numQuestions;
    String title;
}
