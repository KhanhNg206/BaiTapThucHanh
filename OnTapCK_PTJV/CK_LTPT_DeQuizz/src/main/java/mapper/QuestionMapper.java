package mapper;

import dto.QuestionDTO;
import entity.Question;

public class QuestionMapper {

    public static QuestionDTO questionToDTO(Question question){
        if(question == null) return null;
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setQuestionText(question.getQuestionText());
        questionDTO.setQuestionLevel(question.getQuestionLevel());
        questionDTO.setType(question.getType());
        return questionDTO;
    }
}
