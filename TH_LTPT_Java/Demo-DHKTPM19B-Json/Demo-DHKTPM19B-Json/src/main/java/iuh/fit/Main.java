package iuh.fit;

import iuh.fit.entity.Student;
import iuh.fit.mapper.JsonHandler;
import iuh.fit.mapper.StudentJsonMapper;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Student student = Student
                .builder()
                .id(300L)
                .firstName("John")
                .lastName("Doe")
                .dob(LocalDate.of(1989, 3, 2))
                .active(false)
                .phones(new ArrayList<>(List.of("0903 444 555", "0914 555 666")))
                .build();
        System.out.println(student);

        JsonHandler.writeToFile(new File("json/x.json"), StudentJsonMapper.toJsonObject(student));
//        String json = JsonHandler.toJson(StudentJsonMapper.toJsonObject(student));
//        System.out.println(json);

    }
}