//package Exerise;
//
//import Enity2.Quiz;
//import enity.Country;
//import jakarta.json.Json;
//import jakarta.json.JsonArray;
//import jakarta.json.JsonArrayBuilder;
//import jakarta.json.JsonObject;
//
//import java.io.FileWriter;
//
//public class WriterJson02 {
//
//    public static void readFiletoJson(Quiz q , String fileName){
//        try(
//                FileWriter writer = new FileWriter(fileName);
//                ){
//            JsonObject jo = Json.createObjectBuilder()
//                    .add("id",q.getId())
//                    .add("name",q.getName())
//                    .add("score",q.getScore())
//                    .add("questions",Json.createObjectBuilder()
//                            .add("ques_id",q.getId())
//                    .add()
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static JsonArray buildArray(Iterable<String> list) {
//        JsonArrayBuilder builder = Json.createArrayBuilder();
//        list.forEach(builder::add);
//        return builder.build();
//    }
//
//    private static JsonArray doubleArray(Iterable<Double> list){
//        JsonArrayBuilder builder = Json.createArrayBuilder();
//        list.forEach(builder::add);
//        return builder.build();
//    }
//}
