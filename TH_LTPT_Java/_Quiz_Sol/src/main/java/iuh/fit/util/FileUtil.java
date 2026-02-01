package iuh.fit.util;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class FileUtil {

    public static void write2file(String fileName, String data){
        try(
                FileWriter writer = new FileWriter(fileName, true);
                BufferedWriter out = new BufferedWriter(writer);
        ){
            out.append(data);
            out.append("\n");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
