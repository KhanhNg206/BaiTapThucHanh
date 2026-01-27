package mapper;

import entity.Student;
import jakarta.json.*;
import lombok.Lombok;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentJsonMapper {
    //JSON -> POJO

    public static Student formJson(File jsonFile) {
        try (
                FileReader fr = new FileReader(jsonFile);
                JsonReader reader = Json.createReader(fr);
        ) {
            JsonObject jo = reader.readObject();

            long id = jo.getInt("id");
            String firstName = jo.getString("firstName");
            String lastName = jo.getString("lastName");
            boolean isActive = jo.getBoolean("isActive");
            JsonArray js = jo.getJsonArray("phones");
            List<String> phones = new ArrayList<>();
            for (JsonValue jv : js) {
                phones.add(jv.toString());
            }
            JsonObject joDOB = jo.getJsonObject("dob");
            int year = joDOB.getInt("year");
            int month = joDOB.getInt("month");
            int day = joDOB.getInt("day");

            LocalDate dob = LocalDate.of(year, month, day);

            return Student.builder()
                    .id(id)
                    .firstName(firstName)
                    .lastName(lastName)
                    .active(isActive)
                    .phones(phones)
                    .dob(dob)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public  static Student formJson(String json) {
        return null;
    }

    //POJO -> JSON

    public static String toJson(Student student)
    {
        try(
                StringWriter sw = new StringWriter();
                JsonWriter jsonWriter = Json.createWriter(sw);
                ){
            JsonObjectBuilder builder = Json.createObjectBuilder();
            JsonObject jo = builder.add("id",student.getId())
                                    .add("firstName",student.getFirstName())
                                    .add("lastName",student.getLastName())
                                    .add("isActive",student.isActive())
                                    .build();
            jsonWriter.writeObject(jo);
            return sw.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Student> fromJsonArray(File jsonFile) {
        try (
                FileReader fr = new FileReader(jsonFile);
                JsonReader reader = Json.createReader(fr);
        ) {
            JsonArray jsonArray = reader.readArray();
            List<Student> students = new ArrayList<>();

            for (JsonValue value : jsonArray) {
                JsonObject jo = value.asJsonObject();

                long id = jo.getInt("id");
                String firstName = jo.getString("firstName");
                String lastName = jo.getString("lastName");
                boolean isActive = jo.getBoolean("isActive");

                // phones
                List<String> phones = new ArrayList<>();
                JsonArray phoneArray = jo.getJsonArray("phones");
                for (JsonValue jv : phoneArray) {
                    phones.add(((JsonString) jv).getString());
                }

                // dob
                JsonObject dobObj = jo.getJsonObject("dob");
                LocalDate dob = LocalDate.of(
                        dobObj.getInt("year"),
                        dobObj.getInt("month"),
                        dobObj.getInt("day")
                );

                Student student = Student.builder()
                        .id(id)
                        .firstName(firstName)
                        .lastName(lastName)
                        .active(isActive)
                        .phones(phones)
                        .dob(dob)
                        .build();

                students.add(student);
            }

            return students;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJsonArray(List<Student> students) {
        try (
                StringWriter sw = new StringWriter();
                JsonWriter writer = Json.createWriter(sw);
        ) {
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

            for (Student student : students) {

                // phones
                JsonArrayBuilder phoneBuilder = Json.createArrayBuilder();
                for (String phone : student.getPhones()) {
                    phoneBuilder.add(phone);
                }

                // dob
                JsonObjectBuilder dobBuilder = Json.createObjectBuilder()
                        .add("year", student.getDob().getYear())
                        .add("month", student.getDob().getMonthValue())
                        .add("day", student.getDob().getDayOfMonth());

                JsonObject studentObj = Json.createObjectBuilder()
                        .add("id", student.getId())
                        .add("firstName", student.getFirstName())
                        .add("lastName", student.getLastName())
                        .add("isActive", student.isActive())
                        .add("phones", phoneBuilder)
                        .add("dob", dobBuilder)
                        .build();

                arrayBuilder.add(studentObj);
            }

            writer.writeArray(arrayBuilder.build());
            return sw.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static String toJson(Student student, File jsonFile){
        return  null;
    }

//    public static void main(String[] args) {
//        Student student = formJson(new File("D:\\USBDaMat\\LapTrinhHuongSkJava\\STT28_23634741_NguyenHaNhatKhanh_Json\\src\\main\\java\\student.json"));
//        System.out.println(student);
//
//        String json = toJson(student);
//        System.out.println(json);
//
//    }

    public static void main(String[] args) {
        Student student = new Student();
        student.setId(200L);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setActive(true);
        student.setDob(LocalDate.of(1990, 1, 1));
        student.setPhones(new ArrayList<>(List.of("0903 4444 5555", "0901 555 666")));

        String json = toJson(student);
        System.out.println(json);
//        Student student = fromJson(new File("json/student.json"));
//        System.out.println(student);
    }

}
