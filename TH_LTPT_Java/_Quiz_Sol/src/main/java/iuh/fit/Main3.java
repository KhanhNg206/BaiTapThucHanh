package iuh.fit;

import iuh.fit.entity.Category;
import iuh.fit.entity.Quiz;
import iuh.fit.mapper.QuizJsonMapper;
import iuh.fit.util.FileUtil;

import java.io.File;
import java.util.List;

public class Main3 {
    public static void main(String[] args) {
//      Câu 2
        File file = new File("json/quizzes.json");
        String categoryId = "C001";
        List<Quiz> quizzes = QuizJsonMapper.listQuizzes(categoryId, file);
        for (Quiz quiz: quizzes)
            System.out.println(quiz);

//      Câu 3
        String fileName = "output/LeLan23252121.txt";
        for (Quiz quiz: quizzes)
            FileUtil.write2file(fileName, quiz.toString());
    }
}
