package Exerise;

import Enity2.Quiz;
import jakarta.json.*;
import java.io.FileWriter;
import java.util.List;
public class QuizWriter {
    public static void write(List<Quiz> list, String fileName) {

        try (FileWriter file = new FileWriter(fileName)) {

            JsonArrayBuilder arr = Json.createArrayBuilder();

            for (Quiz q : list) {
                JsonObject obj = Json.createObjectBuilder()
                        .add("quiz_id", q.getId())
                        .add("name", q.getName())
                        .add("score", q.getScore())
                        .add("category", Json.createObjectBuilder()
                                .add("category_id", q.getCategory().getCateId())
                                .add("name", q.getCategory().getName()))
                        .build();

                arr.add(obj);
            }

            Json.createWriter(file).writeArray(arr.build());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}