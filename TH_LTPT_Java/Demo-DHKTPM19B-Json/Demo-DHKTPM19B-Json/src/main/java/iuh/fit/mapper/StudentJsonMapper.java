package iuh.fit.mapper;

import iuh.fit.entity.Student;
import iuh.fit.util.DateUtil;
import jakarta.json.*;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//Java Object Model API
public class StudentJsonMapper {

    public static Student fromJson(File jsonFile)  {
        JsonObject jo = JsonHandler.readFromFile(jsonFile);
        return fromJsonObject(jo);
    }

    public static String toJson(Student student) {
        JsonObject jo = toJsonObject(student);
        return JsonHandler.toJson(jo);
    }

    public static Student fromJsonObject(JsonObject jo) {
        JsonArray jaPhones = jo.getJsonArray("phones");
        List<String> phones = new ArrayList<String>();
        for(JsonValue jv : jaPhones){
            if(jv instanceof JsonString){
                JsonString js = (JsonString)jv;
                phones.add(js.getString());
            }
        }
        return Student
                .builder()
                .id(jo.getJsonNumber("id").longValue())
                .phones(phones)
                .firstName(jo.getString("firstName"))
                .lastName(jo.getString("lastName"))
                .active(jo.getBoolean("isActive"))
                .dob(DateUtil.fromJson(jo.getJsonObject("dob")))
                .build();
    }

    public static JsonObject toJsonObject(Student student) {
        JsonObjectBuilder oBuilder = Json.createObjectBuilder();
        JsonArrayBuilder aBuilder = Json.createArrayBuilder();

        List<String> phones = student.getPhones();
        for(String phone : phones){
            aBuilder.add(phone);
        }
        JsonArray jaPhones = aBuilder.build();
        return oBuilder.add("id", student.getId())
                .add("phones",jaPhones)
                .add("firstName", student.getFirstName())
                .add("lastName", student.getLastName())
                .add("isActive", student.isActive())
                .add("dob", DateUtil.toJsonObject(student.getDob()))

                .build();
    }

    public static void main(String[] args) {
        Student student = fromJson(new File("json/student.json"));
        System.out.println(student);

//        Student student = Student
//                .builder()
//                .id(300L)
//                .firstName("John")
//                .lastName("Doe")
//                .dob(LocalDate.of(1989, 3, 2))
//                .active(false)
//                .phones(new ArrayList<>(List.of("0903 444 555", "0914 555 666")))
//                .build();
//        String json = toJson(student);
//        System.out.println(json);
    }

}
