package mapper;

import Enity.Category;
import Enity.Question;
import Enity.Quiz;
import enity2.Country;
import enity2.Name;
import enity2.TranslationDetail;
import jakarta.json.*;

import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.*;

public class ModelJsonWriter {
    public static void JsonWriter(String fileName, List<Country> list){
        try(FileWriter fileWriter = new FileWriter(fileName)){
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

            for(Country c : list){
                JsonArrayBuilder altSpellings = Json.createArrayBuilder();
                for(String altS : c.getAltSpllings()){
                    altSpellings.add(altS);
                }

                JsonArrayBuilder bordersList = Json.createArrayBuilder();
                for(String border : c.getBorder()){
                    bordersList.add(border);
                }

                JsonArrayBuilder callingCodeList = Json.createArrayBuilder();
                for(String callCode : c.getCallingCode()){
                    callingCodeList.add(callCode);
                }

                JsonArrayBuilder currencyList = Json.createArrayBuilder();
                for(String currency : c.getCurrency()){
                    currencyList.add(currency);
                }

                JsonArrayBuilder latlngList = Json.createArrayBuilder();
                for(Double latIng : c.getLatIng()){
                    latlngList.add(latIng);
                }

                JsonObjectBuilder name = Json.createObjectBuilder();
                name.add("common",c.getName().getCommon());
                name.add("official",c.getName().getOffcial());

                JsonObjectBuilder translationsList = Json.createObjectBuilder();
                for (String key : c.getTranslation().keySet()){
                    TranslationDetail t = c.getTranslation().get(key);

                    translationsList.add(key,
                            Json.createObjectBuilder()
                                    .add("common", t.getCommon())
                                    .add("official", t.getOffcial())
                                    .build()
                    );
                }

                JsonObjectBuilder country = Json.createObjectBuilder();
                country.add("id",c.getCountryID());
                country.add("altSpellings",altSpellings.build());
                country.add("area",c.getArea());
                country.add("borders",bordersList.build());
                country.add("callingCode",callingCodeList.build());
                country.add("capital",c.getCapital());
                country.add("cca2",c.getCca2());
                country.add("cioc",c.getCioc());
                country.add("currency",currencyList.build());
                country.add("demonym",c.getDemonym());
                country.add("landLocked",c.getLandLocked());
                country.add("latlng",latlngList.build());
                country.add("name",name.build());
                country.add("region",c.getRegion());
                country.add("subregion",c.getSubregion());
                country.add("translations",translationsList.build());

                JsonObject contryObj = country.build();
                arrayBuilder.add(contryObj);
            }
            JsonWriter jsonWriter = Json.createWriter(fileWriter);
            jsonWriter.writeArray(arrayBuilder.build());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Country> listCountry(String fileName){
        List<Country> result = new ArrayList<>();
        try(
                JsonReader reader = Json.createReader(new FileReader(fileName));
                ) {
            JsonArray jsonArray = reader.readArray();

            for (JsonValue jsonValue : jsonArray){
                JsonObject obj = jsonValue.asJsonObject();

                Country c = new Country();

                List<String> altlist = new ArrayList<>();
                JsonArray altArr = obj.getJsonArray("altSpellings");
                for (JsonValue v : altArr){
                    altlist.add(((JsonString) v).getString());
                }

                List<String> borderList = new ArrayList<>();
                JsonArray borArr = obj.getJsonArray("borders");
                for (JsonValue v : borArr){
                    borderList.add( ( (JsonString)v).getString());
                }

                List<String> callList = new ArrayList<>();
                JsonArray callArr = obj.getJsonArray("callingCode");
                for(JsonValue v : callArr){
                    callList.add(((JsonString)v).getString());
                }

                List<String> currList = new ArrayList<>();
                JsonArray currArr = obj.getJsonArray("currency");
                for (JsonValue v : currArr){
                    currList.add(((JsonString)v).getString());
                }

                List<Double> latList = new ArrayList<>();
                JsonArray latArr = obj.getJsonArray("latlng");
                for(JsonValue v : latArr){
                    latList.add(((JsonNumber)v).doubleValue());
                }

                JsonObject nameObj = obj.getJsonObject("name");
                Name name = new Name();
                name.setCommon(nameObj.getString("common"));
                name.setOffcial(nameObj.getString("official"));

                Map<String,TranslationDetail> map = new HashMap<>();
                JsonObject transObj = obj.getJsonObject("translations");
                for(String key : transObj.keySet()){
                    JsonObject tObj = transObj.getJsonObject(key);
                    TranslationDetail trans = new TranslationDetail();
                    trans.setCommon(tObj.getString("common"));
                    trans.setCommon(tObj.getString("official"));
                    map.put(key,trans);
                }

                c.setCountryID(obj.getString("id"));
                c.setAltSpllings(altlist);
                c.setArea(obj.getString("area"));
                c.setBorder(borderList);
                c.setCallingCode(callList);
                c.setCapital(obj.getString("capital"));
                c.setCca2(obj.getString("cca2"));
                c.setCioc(obj.getString("cioc"));
                c.setCurrency(currList);
                c.setDemonym(obj.getString("demonym"));
                c.setLandLocked(obj.getBoolean("landLocked"));
                c.setLatIng(latList);
                c.setName(name);
                c.setRegion(obj.getString("region"));
                c.setSubregion(obj.getString("subregion"));
                c.setTranslation(map);

                result.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<Quiz> ListQuizModel(String fileName){
        List<Quiz> result = new ArrayList<>();

        try (JsonReader reader = Json.createReader(new FileReader(fileName));) {
           JsonArray arrQuiz = reader.readArray();
           for (JsonValue v : arrQuiz){
               JsonObject obj = v.asJsonObject();
               Quiz quiz = new Quiz();

               //xử lý Question
               List<Question> questionList = new ArrayList<>();
               JsonArray questionArr = obj.getJsonArray("questions");
               for(JsonValue questionValue : questionArr){
                   JsonObject questionObject = questionValue.asJsonObject();
                   Question question = new Question();

                   List<String> optionsList = new ArrayList<>();
                   JsonArray optionArr = questionObject.getJsonArray("options");
                   for (JsonValue optionValue : optionArr){
                       optionsList.add(((JsonString)optionValue).getString());
                   }
                   question.setQuesId(questionObject.getString("question_id"));
                   question.setText(questionObject.getString("text"));
                   question.setOptions(optionsList);
                   question.setCorrect_answer(questionObject.getString("correct_answer"));

                   questionList.add(question);
               }

               //xử lý category
               JsonObject cateObject = obj.getJsonObject("category");
               Category category = new Category();
               category.setCateID(cateObject.getString("category_id"));
               category.setName(cateObject.getString("name"));

               //xử lý quiz
               quiz.setQuizId(obj.getString("quiz_id"));
               quiz.setName(obj.getString("name"));
               quiz.setScore(obj.getInt("score"));
               quiz.setQuestion(questionList);
               quiz.setCategory(category);

               result.add(quiz);
           }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void QuizToJson(List<Quiz> listQuiz,String fileName){
        try (
                FileWriter fileWriter = new FileWriter(fileName);
                ){
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            JsonObjectBuilder quizObject = Json.createObjectBuilder();
            for(Quiz quiz : listQuiz){

                JsonArrayBuilder questionArr = Json.createArrayBuilder();
                JsonObjectBuilder questionObject = Json.createObjectBuilder();
                for (Question ques : quiz.getQuestion()){
                    questionObject.add("question_id",ques.getQuesId());
                    questionObject.add("text",ques.getText());
                    JsonArrayBuilder optionsArr = Json.createArrayBuilder();
                    for (String op : ques.getOptions()){
                        optionsArr.add(op);
                    }
                    questionObject.add("options",optionsArr);
                    questionObject.add("correct_answer",ques.getCorrect_answer());

                    questionArr.add(questionObject);
                }

                JsonObjectBuilder cateObject = Json.createObjectBuilder();
                cateObject.add("category_id",quiz.getCategory().getCateID());
                cateObject.add("name",quiz.getCategory().getName());

                quizObject.add("quiz_id",quiz.getQuizId());
                quizObject.add("name",quiz.getName());
                quizObject.add("score",quiz.getScore());
                quizObject.add("questions",questionArr);
                quizObject.add("category",cateObject);

                arrayBuilder.add(quizObject.build());
            }

            JsonWriter jsonWriter = Json.createWriter(fileWriter);
            jsonWriter.writeArray(arrayBuilder.build());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<Quiz> list = ListQuizModel("src/main/java/data/quizzes.json");
        QuizToJson(list,"src/main/java/data/result.json");

//List<Quiz> list = ListQuizModel("src/main/java/data/quizzes.json");
//List<Quiz> list2 = StreamingAPIJson.WriteJson("src/main/java/data/quizzes.json","123");
//System.out.println(list);
//System.out.println("=================================");
//System.out.println(list2);

//        Country country = new Country();
//        country.setCountryID("1");
//        country.setAltSpllings(Arrays.asList(  "VN",
//                "Socialist Republic of Vietnam",
//                "Cộng hòa Xã hội chủ nghĩa Việt Nam",
//                "Viet Nam"));
//        country.setArea("331212");
//        country.setBorder(Arrays.asList("KHM","CHN","LAO"));
//        country.setCallingCode(Arrays.asList("84"));
//        country.setCapital("Hanoi");
//        country.setCca2("VN");
//        country.setCioc("VIE");
//        country.setCurrency(Arrays.asList("VND"));
//        country.setDemonym("Vietnamese");
//        country.setLandLocked(false);
//        country.setLatIng(Arrays.asList(16.16666666,107.83333333));
//        Name n = new Name();
//        n.setCommon("Vietname");
//        n.setOffcial("Socialist Republic of Vietnam" );
//        country.setName(n);
//        country.setRegion("Asia");
//        country.setSubregion( "South-Eastern Asia");
//        Map<String,TranslationDetail> map = new HashMap<>();
//        map.put("fra",new TranslationDetail("Viêt Nam", "République socialiste du Vietnam"));
//        map.put("ita",new TranslationDetail("Viêt Nam", "République socialiste du Vietnam"));
//        country.setTranslation(map);
//
//        List<Country> ctl = new ArrayList<>();
//        ctl.add(country);
//        ModelJsonWriter.JsonWriter("src/main/java/data/result.json",ctl);
        //test hanoi
//        try (JsonReader reader = Json.createReader(new FileReader(fileName))) {
//            JsonArray array = reader.readArray();
//
//            JsonObject firstCountry = array.getJsonObject(0);
//
//            String capital = firstCountry.getString("capital");
//
//            if (capital.equals("Hanoi")) {
//                System.out.println("PASS");
//            } else {
//                System.out.println("FAIL");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
}
