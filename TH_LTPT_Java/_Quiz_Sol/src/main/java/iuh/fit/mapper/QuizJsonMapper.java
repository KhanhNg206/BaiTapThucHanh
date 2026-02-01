package iuh.fit.mapper;

import iuh.fit.entity.Category;
import iuh.fit.entity.Question;
import iuh.fit.entity.Quiz;
import jakarta.json.Json;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

//Streaming API
public class QuizJsonMapper {
    public static List<Quiz> listQuizzes (String categoryId, File jsonFile){

        String currentObject = "";
        String keyName = "";
        Quiz quiz = null;
        Question question = null;
        List<Quiz> quizzes = null;

        try(
                FileReader reader = new FileReader(jsonFile);
                JsonParser parser = Json.createParser(reader);
        ){

            while (parser.hasNext()){
                JsonParser.Event event = parser.next();
                switch (event){
                    case START_OBJECT -> {
                        if("quizzes".equals(currentObject)){
                            quiz = new Quiz();
                            currentObject = "quiz";
                        }else if("category".equals(keyName)){
                            quiz.setCategory(new Category());
                            currentObject = "category";
                        }else if("questions".equals(currentObject)){
                            question = new Question();
                            currentObject = "question";
                        }
                    }
                    case END_OBJECT -> {
                        if("question".equals( currentObject)){
                            quiz.getQuestions().add(question);
                            currentObject = "questions";
                        }else if("quiz".equals(currentObject)) {
                            if(quiz.getCategory().getCategoryId().equalsIgnoreCase(categoryId))
                                quizzes.add(quiz);
                            currentObject = "quizzes";
                        }else if("category".equals(currentObject)){
                            currentObject = "quiz";
                        }
                    }
                    case START_ARRAY -> {
                        if("".equals(keyName)){
                            quizzes = new ArrayList<>();
                            currentObject = "quizzes";
                        }else if("questions".equals(keyName)){
                            quiz.setQuestions(new ArrayList<>());
                            currentObject = "questions";
                        }else if("options".equals(keyName)){
                            question.setOptions(new ArrayList<>());
                            currentObject = "options";
                        }
                    }
                    case END_ARRAY -> {
                        if("options".equals(currentObject)){
                            currentObject = "question";
                        }else if("questions".equals(currentObject))
                            currentObject = "quiz";
                        else if("quizzes".equals(currentObject)){
                            return quizzes;
                        }
                    }
                    case KEY_NAME -> {
                        keyName = parser.getString();
//                        System.out.println(keyName);
                    }
                    case VALUE_STRING -> {
                        String value = parser.getString();
                        switch (keyName){
                            case "quiz_id" -> quiz.setQuizId(value);
                            case "name" -> {
                                if("quiz".equals(currentObject)){
                                    quiz.setName(value);
                                }else if("category".equals(currentObject))
                                    quiz.getCategory().setName(value);
                            }
                            case "question_id" -> question.setQuestionId(value);
                            case "text" -> question.setText(value);
                            case "correct_answer" -> question.setCorrectAnswer(value);
                            case "options" -> question.getOptions().add(value);
                            case "category_id" -> quiz.getCategory().setCategoryId(value);
                        }
                    }
                    case VALUE_NUMBER -> quiz.setScore(parser.getInt());
                    default -> {}
                }
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

}
