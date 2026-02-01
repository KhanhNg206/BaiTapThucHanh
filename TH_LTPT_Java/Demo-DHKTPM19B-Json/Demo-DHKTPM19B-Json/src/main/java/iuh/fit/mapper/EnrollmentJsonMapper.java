package iuh.fit.mapper;

import iuh.fit.entity.Course;
import iuh.fit.entity.Enrollment;
import iuh.fit.entity.Student;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentJsonMapper {

    public static String toJson(Enrollment enrollment) {
        JsonObject jo = toJsonObject(enrollment);
        return JsonHandler.toJson(jo);
    }

    public static Enrollment fromJson(File jsonFile) {
        JsonObject jo = JsonHandler.readFromFile(jsonFile);
        return fromJsonObject(jo);
    }

    public static Enrollment fromJsonObject(JsonObject jo) {
        return Enrollment.builder()
                .student(StudentJsonMapper.fromJsonObject(jo.getJsonObject("student")))
                .course(CourseJsonMapper.fromJsonObject(jo.getJsonObject("course")))
                .grade(jo.getJsonNumber("grade").doubleValue())
                .build();
    }

    public static JsonObject toJsonObject(Enrollment enrollment) {
        return Json.createObjectBuilder()
                .add("student", StudentJsonMapper.toJsonObject(enrollment.getStudent()))
                .add("course", CourseJsonMapper.toJsonObject(enrollment.getCourse()))
                .add("grade", enrollment.getGrade())
                .build();
    }

    public static void main(String[] args) {
        Course course = Course
                .builder()
                .id("CS110")
                .name("Computer Science Basics")
                .credits(3)
                .build();
        Student student = Student
                .builder()
                .id(300L)
                .firstName("John")
                .lastName("Doe")
                .dob(LocalDate.of(1989, 3, 2))
                .active(false)
                .phones(new ArrayList<>(List.of("0903 444 555", "0914 555 666")))
                .build();
        Enrollment  enrollment = Enrollment
                .builder()
                .student(student)
                .course(course)
                .grade(8.5)
                .build();

        String json = toJson(enrollment);
        System.out.println(json);

//        Enrollment enrollment = fromJson(new File("json/enrollment.json"));
//        System.out.println(enrollment);

    }
}