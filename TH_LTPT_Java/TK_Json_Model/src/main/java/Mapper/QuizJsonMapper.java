package Mapper;

import Enity2.Category;
import Enity2.Questions;
import Enity2.Quiz;
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
        Questions question = null;
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
                            question = new Questions();
                            currentObject = "question";
                        }
                    }
                    case END_OBJECT -> {
                        if("question".equals( currentObject)){
                            quiz.getQues().add(question);
                            currentObject = "questions";
                        }else if("quiz".equals(currentObject)) {
                            if(quiz.getCategory().getCateId().equalsIgnoreCase(categoryId))
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
                            quiz.setQues(new ArrayList<>());
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
                            case "quiz_id" -> quiz.setId(value);
                            case "name" -> {
                                if("quiz".equals(currentObject)){
                                    quiz.setName(value);
                                }else if("category".equals(currentObject))
                                    quiz.getCategory().setName(value);
                            }
                            case "question_id" -> question.setQuesId(value);
                            case "text" -> question.setText(value);
                            case "correct_answer" -> question.setCorrect(value);
                            case "options" -> question.getOptions().add(value);
                            case "category_id" -> quiz.getCategory().setCateId(value);
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
