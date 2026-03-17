package Exerise;

import Enity2.Category;
import Enity2.Questions;
import Enity2.Quiz;
import jakarta.json.Json;
import jakarta.json.stream.JsonParser;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class StreamingJson {

    public static List<Quiz> listQuizzes(String cateID, File jsonFile){
        List<Quiz> result = new ArrayList<>();

        Category cate = null;
        Quiz quiz = null;
        Questions question = null;
        List<Questions> questions = null;
        List<String> options = null;

        try(InputStream is = jsonFile.toURI().toURL().openStream()){

            JsonParser parser = Json.createParser(is);

            while(parser.hasNext()){
                JsonParser.Event e = parser.next();

                if(e == JsonParser.Event.KEY_NAME){
                    switch(parser.getString()){

                        case "quiz_id":
                            parser.next();
                            quiz = new Quiz();
                            quiz.setId(parser.getString());
                            break;

                        case "name":
                            parser.next();
                            String name = parser.getString();

                            if (quiz != null && quiz.getName() == null){
                                quiz.setName(name); // name của quiz
                            } else if (cate != null){
                                cate.setName(name); // name của category
                            }
                            break;

                        case "score":
                            parser.next();
                            quiz.setScore(parser.getInt());
                            break;

                        case "questions":
                            questions = new ArrayList<>();
                            break;

                        case "question_id":
                            parser.next();
                            question = new Questions();
                            question.setQuesId(parser.getString());
                            break;

                        case "text":
                            parser.next();
                            if(question != null){
                                question.setText(parser.getString());
                            }
                            break;

                        case "options":
                            options = new ArrayList<>();
                            break;

                        case "correct_answer":
                            parser.next();
                            if(question != null){
                                question.setCorrect(parser.getString());
                            }
                            break;

                        case "category_id":
                            parser.next();
                            cate = new Category();
                            cate.setCateId(parser.getString());
                            break;
                    }
                }

                // đọc options
                if(e == JsonParser.Event.VALUE_STRING){
                    if(options != null){
                        options.add(parser.getString());
                    }
                }

                // xử lý kết thúc object
                if(e == JsonParser.Event.END_OBJECT){

                    // kết thúc 1 question
                    if(question != null){
                        question.setOptions(options);
                        questions.add(question);

                        question = null;
                        options = null;
                    }

                    // kết thúc category → hoàn thành quiz
                    if(cate != null && quiz != null){
                        quiz.setCategory(cate);
                        quiz.setQues(questions);

                        if(cateID.equals(cate.getCateId())){
                            result.add(quiz);
                        }

                        cate = null;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}