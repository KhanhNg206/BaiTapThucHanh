package util;

import Enity2.Quiz;
import Mapper.QuizJsonMapper;

import java.io.File;
import java.util.List;

public class Main3 {
    public static void main(String[] args) {
//      Câu 2
        File file = new File("src/main/java/data/quiz.json");
        String categoryId = "C001";
        List<Quiz> quizzes = QuizJsonMapper.listQuizzes(categoryId, file);
        for (Quiz quiz: quizzes)
            System.out.println(quiz);

//      Câu 3
        String fileName = "src/main/java/data/quizResult.json";
        for (Quiz quiz: quizzes)
            FileUtil.write2file(fileName, quiz.toString());
    }
}
