package Exerise;

import Enity2.Category;
import Enity2.Questions;
import Enity2.Quiz;
import jakarta.json.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class StreamChangeModel {

    public static List<Quiz> listQuiz(String cateID, File jsonFile){
        List<Quiz> result = new ArrayList<>();

        try(JsonReader reader = Json.createReader(new FileInputStream(jsonFile))){

            JsonArray quizArray = reader.readArray(); // giả sử file là mảng []

            for(JsonValue qVal : quizArray){
                JsonObject qObj = qVal.asJsonObject();

                // ===== Quiz =====
                Quiz quiz = new Quiz();
                quiz.setId(qObj.getString("quiz_id"));
                quiz.setName(qObj.getString("name"));
                quiz.setScore(qObj.getInt("score"));

                // ===== Category =====
                JsonObject cateObj = qObj.getJsonObject("category");

                Category cate = new Category();
                cate.setCateId(cateObj.getString("category_id"));
                cate.setName(cateObj.getString("name"));

                // lọc theo cateID
                if(!cateID.equals(cate.getCateId())){
                    continue;
                }

                quiz.setCategory(cate);

                // ===== Questions =====
                List<Questions> questions = new ArrayList<>();
                JsonArray quesArr = qObj.getJsonArray("questions");

                for(JsonValue quesVal : quesArr){
                    JsonObject quesObj = quesVal.asJsonObject();

                    Questions question = new Questions();
                    question.setQuesId(quesObj.getString("question_id"));
                    question.setText(quesObj.getString("text"));
                    question.setCorrect(quesObj.getString("correct_answer"));

                    // options
                    List<String> options = new ArrayList<>();
                    JsonArray optArr = quesObj.getJsonArray("options");

                    for(JsonValue opt : optArr){
                        options.add(opt.toString().replace("\"",""));
                    }

                    question.setOptions(options);

                    questions.add(question);
                }

                quiz.setQues(questions);

                result.add(quiz);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }
}