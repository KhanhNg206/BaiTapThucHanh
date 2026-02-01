package iuh.fit.mapper;

import jakarta.json.*;
import jakarta.json.stream.JsonGenerator;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.Map;

//Java Object Model
public class JsonHandler {
    private static final Map<String, ?> CONFIG = Map.of(
            JsonGenerator.PRETTY_PRINTING, true
    );
    private static JsonWriterFactory FACTORY =
            Json.createWriterFactory(CONFIG);

    public static JsonObject readFromFile(File jsonFile){
        try(
                FileReader fr = new  FileReader(jsonFile);
                JsonReader reader = Json.createReader(fr);
                ){
                return reader.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static JsonArray readFromArrayFile(File jsonFile){
        try(
                FileReader fr = new  FileReader(jsonFile);
                JsonReader reader = Json.createReader(fr);
        ){
            return reader.readArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void writeToFile(File jsonFile, JsonObject jsonObject){
        try(
                FileWriter fw = new  FileWriter(jsonFile);
//                JsonWriter writer = Json.createWriter(fw);
                JsonWriter writer = FACTORY.createWriter(fw);
                ){
                writer.write(jsonObject);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static String toJson(JsonObject jsonObject){
        try(
                StringWriter fw = new  StringWriter();
//                JsonWriter writer = Json.createWriter(fw);
                JsonWriter writer = FACTORY.createWriter(fw);
                ){
                writer.write(jsonObject);
                return fw.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void writeToFile(File jsonFile, JsonArray jsonArray){
        try(
                FileWriter fw = new  FileWriter(jsonFile);
                JsonWriter writer = Json.createWriter(fw);
        ){
            writer.write(jsonArray);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        JsonObject jo = readFromFile(new File("json/course.json"));
        JsonArray ja = readFromArrayFile(new File("json/courses.json"));
        System.out.println(jo.toString());
        System.out.println(ja.toString());
    }
}
