package iuh.fit.mapper;

import iuh.fit.entity.Course;
import jakarta.json.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
//Java Object Model API
public class CourseJsonMapper {
    //Json-data -> POJO (Course)
    public static Course fromJson(File jsonFile){
        JsonObject jo = JsonHandler.readFromFile(jsonFile);
        return fromJsonObject(jo);
    }

    public String toJson(Course course){
        JsonObject jo = toJsonObject(course);
        return JsonHandler.toJson(jo);
    }

    public static List<Course> fromJsonArray(File jsonFile){
        JsonArray ja = JsonHandler.readFromArrayFile(jsonFile);
        List<Course> courses = new ArrayList<>();
        for(JsonValue jv : ja){
            if(jv instanceof JsonObject){
                JsonObject jo = (JsonObject)jv;
                Course course = fromJsonObject(jo);
                courses.add(course);
            }
        }
        return  courses;
    }

    public static Course fromJsonObject(JsonObject jo) {
        return Course
                .builder()
                .name(jo.getString("name"))
                .id(jo.getString("id"))
                .credits(jo.getInt("credits"))
                .build();
    }

    public static JsonObject toJsonObject(Course course){
        JsonObjectBuilder oBuilder = Json.createObjectBuilder();
        return oBuilder.add("name", course.getName())
                .add("id", course.getId())
                .add("credits", course.getCredits())
                .build();

    }

    public static void main(String[] args) {
        Course course = fromJson(new File("json/course.json"));
        System.out.println(course);

        List<Course> courses = fromJsonArray(new File("json/courses.json"));
        System.out.println(courses);

//        Course course = Course
//                .builder()
//                .id("C001")
//                .name("Java Programming")
//                .credits(3)
//                .build();
//        String json = new CourseJsonMapper().toJson(course);
//        System.out.println(json);
    }
}
