package service;

import dto.QuestionDTO;
import entity.Level;
import entity.Question;

import java.util.List;
import java.util.Map;

public interface Service {
    List<QuestionDTO> listQuestionByLevelAndCategory(String categoryName, Level questionLevel);

    Map<Level, Long> countQuestionsByLevelInQuiz(String quizId);

    QuestionDTO addQuestionToCategory(Question question, String categoryId);
}
