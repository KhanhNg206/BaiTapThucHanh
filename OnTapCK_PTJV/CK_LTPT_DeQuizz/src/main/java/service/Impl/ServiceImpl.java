package service.Impl;

import dao.Dao;
import dto.QuestionDTO;
import entity.Level;
import entity.Question;
import mapper.QuestionMapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ServiceImpl implements service.Service {
    private Dao dao;

    public ServiceImpl() {
        this.dao = new Dao();
    }

    @Override
    public List<QuestionDTO> listQuestionByLevelAndCategory(String categoryName, Level questionLevel){
        return dao.listQuestionByLevelAndCategory(categoryName,questionLevel)
                .stream()
                .map(QuestionMapper::questionToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Level,Long> countQuestionsByLevelInQuiz(String quizId){
        return dao.countQuestionsByLevelInQuiz(quizId);
    }

    @Override
    public QuestionDTO addQuestionToCategory(Question question, String categoryId){
        return QuestionMapper.questionToDTO(dao.addQuestionToCategory(question,categoryId));
    }


}
