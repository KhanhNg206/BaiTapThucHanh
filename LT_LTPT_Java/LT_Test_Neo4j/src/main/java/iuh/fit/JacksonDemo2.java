package iuh.fit;

import com.fasterxml.jackson.databind.ObjectMapper;
import iuh.fit.core.entity.Student;

import java.util.Map;

public class JacksonDemo2 {
    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map = Map.of(
                "student_id", "23634741",
                "name", "Nhat Khanh",
                "gpa", 3.6
        );

        Student student = mapper.convertValue(map, Student.class);

        System.out.println(student);
    }
}