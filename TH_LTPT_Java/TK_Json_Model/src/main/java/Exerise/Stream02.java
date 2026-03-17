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

public class Stream02 {
 public static List<Quiz> listQuiz(String cateID, File jsonFile){
     List<Quiz> result = new ArrayList<>();
     Quiz quiz = null;
     List<String> options = null;
     Category cate = null;
     Questions question = null;
     List<Questions> questions = null;
     try(InputStream is = jsonFile.toURL().openStream();
         JsonParser parser = Json.createParser(is)){
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
                         if(quiz != null && quiz.getName() == null ){
                             quiz.setName(parser.getString());
                         }else if(cate.getName() == null){
                             cate.setName(parser.getString());
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
                         question.setText(parser.getString());
                         break;
                     case "options":
                         options = new ArrayList<>();
                         break;
                     case "correct_answer":
                         parser.next();
                         question.setCorrect(parser.getString());
                         break;
                     case "category_id":
                         parser.next();
                         cate = new Category();
                         cate.setCateId(parser.getString());
                         break;
                 }
             }
             if(e == JsonParser.Event.VALUE_STRING){
                 if(options != null){
                     options.add(parser.getString());
                 }
             }
             if(e == JsonParser.Event.END_OBJECT){
                 if(question != null){
                     question.setOptions(options);
                     questions.add(question);

                     question = null;
                     options = null;
                 }

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
