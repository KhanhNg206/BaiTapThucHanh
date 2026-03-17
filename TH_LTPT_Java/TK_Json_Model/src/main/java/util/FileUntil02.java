package util;

import Enity2.Quiz;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class FileUntil02 {
    public static void saveAsFile(Quiz quiz,String filePath){
        try(
                FileWriter writer = new FileWriter(filePath);
                BufferedWriter out = new BufferedWriter(writer);
                ){
            out.append(quiz.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
