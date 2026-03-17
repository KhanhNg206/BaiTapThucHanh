package Exerise;
import Enity2.Quiz;

import java.io.File;
import java.util.List;
public class testStreaming {

        public static void main(String[] args) {

            File file = new File("src/main/java/data/quiz.json");

            List<Quiz> list = StreamingJson.listQuizzes("C006",file);

            // in ra kiểm tra
            for (Quiz q : list) {
                System.out.println(q.getName());
            }

            // ghi file
            QuizWriter.write(list, "src/main/java/data/quizResult.json");
        }
    }
