package util;

import Enity2.Quiz;
import Exerise.Stream02;
import Exerise.StreamChangeModel;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class Main04 {
    public static void main(String[] args) {
//        File file = new File("src/main/java/data/quiz.json");
//        List<Quiz> quizs = Stream02.listQuiz("C006",file);
//        for (Quiz quiz : quizs){
//            System.out.println(quiz);
//            FileUntil02.saveAsFile(quiz,"src/main/java/data/quizTest.txt");
//        }

       try {
           File file = new File("src/main/java/data/quiz.json");
           List<Quiz> quizs = StreamChangeModel.listQuiz("C006",file);
           for (Quiz quiz : quizs){
               System.out.println(quiz);
               FileUntil02.saveAsFile(quiz,"src/main/java/data/quizTest.txt");
           }
       } catch (Exception e) {
           e.printStackTrace();
       }


    }
}
