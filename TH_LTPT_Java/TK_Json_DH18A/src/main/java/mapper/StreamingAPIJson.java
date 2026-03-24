package mapper;

import Enity.Category;
import Enity.Question;
import Enity.Quiz;
import enity2.Country;
import enity2.Name;
import enity2.TranslationDetail;
import jakarta.json.Json;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;

import java.io.*;
import java.util.*;

public class StreamingAPIJson {

    public static List<Quiz> WriteJson(String jsonFile , String cateID) {
        List<Quiz> result = new ArrayList<>();

        Quiz quiz = null;
        List<Question> questionsList = null;
        Question question = null;
        List<String> optionList = null;
        Category category = null;

        boolean inOptions = false;

        try(JsonParser parser = Json.createParser(new FileReader(jsonFile))) {

            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();

                switch (event) {
                    case KEY_NAME:
                        String key = parser.getString();

                        switch (key) {
                            case "quiz_id":
                                parser.next();
                                quiz = new Quiz();
                                questionsList = new ArrayList<>();
                                quiz.setQuizId(parser.getString());
                                break;

                            case "name":
                                parser.next();
                                if (category != null) category.setName(parser.getString());
                                else if (quiz != null) quiz.setName(parser.getString());
                                break;

                            case "score":
                                parser.next();
                                quiz.setScore(parser.getInt());
                                break;

                            case "question_id":
                                parser.next();
                                question = new Question();
                                question.setQuesId(parser.getString());
                                break;

                            case "text":
                                parser.next();
                                question.setText(parser.getString());
                                break;

                            case "options":
                                optionList = new ArrayList<>();
                                inOptions = true;
                                break;

                            case "correct_answer":
                                parser.next();
                                question.setCorrect_answer(parser.getString());
                                break;

                            case "category_id":
                                parser.next();
                                category = new Category();
                                category.setCateID(parser.getString());
                                break;
                        }
                        break;

                    case VALUE_STRING:
                        if (inOptions) {
                            optionList.add(parser.getString());
                        }
                        break;

                    case END_ARRAY:
                        if (inOptions) {
                            question.setOptions(optionList);
                            inOptions = false;
                        }
                        break;

                    case END_OBJECT:
                        if (question != null) {
                            questionsList.add(question);
                            question = null;
                        } else if (category != null) {
                            quiz.setCategory(category);
                            category = null;
                        } else if (quiz != null) {
                            quiz.setQuestion(questionsList);

//                            if (quiz.getCategory() != null &&
//                                    cateID.equals(quiz.getCategory().getCateID())) {
//                                result.add(quiz);
//                            }
                            result.add(quiz);

                            quiz = null;
                        }
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void writeFile(String fileName, List<Quiz> data){
        try(
                FileWriter writer = new FileWriter(fileName);
                JsonGenerator gen = Json.createGenerator(writer);
                ){
            gen.writeStartArray();
            for (Quiz quiz : data){
                gen.writeStartObject()
                        .write("quiz_id",quiz.getQuizId())
                        .write("name",quiz.getName())
                        .write("score",quiz.getScore());

                gen.writeStartArray("questions");
                for (Question q : quiz.getQuestion()){
                    gen.writeStartObject()
                            .write("question_id",q.getQuesId())
                            .write("text",q.getText());
                    gen.writeStartArray("options");
                    for (String op : q.getOptions()){
                        gen.write(op);
                    }
                    gen.writeEnd();

                    gen.write("correct_answer",q.getCorrect_answer());
                    gen.writeEnd();
                }
                gen.writeEnd();

                gen.writeStartObject("category")
                .write("category_id",quiz.getCategory().getCateID())
                .write("name",quiz.getCategory().getName())
                .writeEnd();

                gen.writeEnd();
            }
            gen.writeEnd();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void write2file( List<Country> data,String fileName){
        try(
                FileWriter writer = new FileWriter(fileName, true);
                BufferedWriter out = new BufferedWriter(writer);
        ){
            out.append(data.toString());
            out.append("\n");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static List<Country> ListCountry(String fileName){
        List<Country> result = new ArrayList<>();

        try (JsonParser parser = Json.createParser(new FileReader(fileName))) {

            Country country = null;
            List<String> borderList = null;
            List<String> altList = null;
            List<String> callCode = null;
            List<String> currencyList = null;
            List<Double> latIngfList = null;

            boolean altFlag = false, borderFlag = false, callCodeFlag = false;
            boolean currFlag = false, latIngFlag = false;

            Name name = null;

            Map<String, TranslationDetail> transMap = null;
            TranslationDetail translationDetail = null;
            String transKey = null;
            boolean transFlag = false;

            int objectLevel = 0;

            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();

                switch(event) {

                    case START_OBJECT:
                        objectLevel++;
                        if (objectLevel == 1) {
                            country = new Country();
                        } else if (transFlag && objectLevel == 3) {
                            translationDetail = new TranslationDetail();
                        }
                        break;

                    case END_OBJECT:
                        if (transFlag && objectLevel == 3) {
                            transMap.put(transKey, translationDetail);
                            translationDetail = null;
                            transKey = null;
                        }
                        else if (transFlag && objectLevel == 2) {
                            country.setTranslation(transMap);
                            transFlag = false;
                        }
                        else if (objectLevel == 1) {
                            result.add(country);
                            country = null;
                        }
                        objectLevel--;
                        break;

                    case KEY_NAME:
                        String key = parser.getString();

                        switch (key) {
                            case "id":
                                parser.next();
                                country.setCountryID(parser.getString());
                                break;

                            case "area":
                                parser.next();
                                country.setArea(parser.getString());
                                break;

                            case "capital":
                                parser.next();
                                country.setCapital(parser.getString());
                                break;

                            case "cca2":
                                parser.next();
                                country.setCca2(parser.getString());
                                break;

                            case "cioc":
                                parser.next();
                                country.setCioc(parser.getString());
                                break;

                            case "demonym":
                                parser.next();
                                country.setDemonym(parser.getString());
                                break;

                            case "landLocked":
                                JsonParser.Event e = parser.next();
                                country.setLandLocked(e == JsonParser.Event.VALUE_TRUE);
                                break;

                            case "region":
                                parser.next();
                                country.setRegion(parser.getString());
                                break;

                            case "subregion":
                                parser.next();
                                country.setSubregion(parser.getString());
                                break;

                            case "altSpellings":
                                altList = new ArrayList<>();
                                altFlag = true;
                                break;

                            case "borders":
                                borderList = new ArrayList<>();
                                borderFlag = true;
                                break;

                            case "callingCode":
                                callCode = new ArrayList<>();
                                callCodeFlag = true;
                                break;

                            case "currency":
                                currencyList = new ArrayList<>();
                                currFlag = true;
                                break;

                            case "latlng":
                                latIngfList = new ArrayList<>();
                                latIngFlag = true;
                                break;

                            case "name":
                                name = new Name();
                                break;

                            case "translations":
                                transMap = new HashMap<>();
                                transFlag = true;
                                break;

                            case "official":
                                parser.next();
                                if (translationDetail != null)
                                    translationDetail.setOffcial(parser.getString());
                                if (name != null)
                                    name.setOffcial(parser.getString());
                                break;

                            case "common":
                                parser.next();
                                if (translationDetail != null)
                                    translationDetail.setCommon(parser.getString());
                                if (name != null)
                                    name.setCommon(parser.getString());
                                break;

                            default:
                                if (transFlag && objectLevel == 2) {
                                    transKey = key;
                                }
                                break;
                        }
                        break;

                    case VALUE_STRING:
                        if (altFlag) altList.add(parser.getString());
                        if (borderFlag) borderList.add(parser.getString());
                        if (callCodeFlag) callCode.add(parser.getString());
                        if (currFlag) currencyList.add(parser.getString());
                        break;

                    case VALUE_NUMBER:
                        if (latIngFlag)
                            latIngfList.add(parser.getBigDecimal().doubleValue());
                        break;

                    case END_ARRAY:
                        if (altFlag) {
                            country.setAltSpllings(altList);
                            altFlag = false;
                        }
                        if (borderFlag) {
                            country.setBorder(borderList);
                            borderFlag = false;
                        }
                        if (callCodeFlag) {
                            country.setCallingCode(callCode);
                            callCodeFlag = false;
                        }
                        if (currFlag) {
                            country.setCurrency(currencyList);
                            currFlag = false;
                        }
                        if (latIngFlag) {
                            country.setLatIng(latIngfList);
                            latIngFlag = false;
                        }
                        break;
                }

                // set name sau khi đọc xong object name
                if (name != null && objectLevel == 1) {
                    country.setName(name);
                    name = null;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
//                // translations
//                if (c.getTranslation() != null) {
//                    gen.writeStartObject("translations");
//
//                    for (Map.Entry<String, TranslationDetail> entry : c.getTranslation().entrySet()) {
//                        TranslationDetail t = entry.getValue();
//
//                        gen.writeStartObject(entry.getKey())
//                                .write("common", t.getCommon())
//                                .write("official", t.getOffcial())
//                                .writeEnd();
//                    }
//
//                    gen.writeEnd();
//                }



    public static void main(String[] args) {
        List<Country> data = ListCountry("src/main/java/data/country.json");
        write2file( data,"src/main/java/data/result.json");
//        List<Quiz> data = WriteJson("src/main/java/data/quizzes.json","C001");
//        writeFile("src/main/java/data/result.json",data);

//
//          Quiz quiz = new Quiz();
//          quiz.setQuizId("quiz-001");
//          quiz.setName("Khanh");
//          quiz.setScore(123);
//          Question ques = new Question("ques01","text text", Arrays.asList("4","6","8"),"22");
//          List<Question> quesList = new ArrayList<>();
//          quesList.add(ques);
//          quiz.setQuestion(quesList);
//          quiz.setCategory(new Category("C001","Java"));
//
//          List<Quiz> data = new ArrayList<>();
//          data.add(quiz);
//
//          writeFile("src/main/java/data/result.json",data);

    }
}
