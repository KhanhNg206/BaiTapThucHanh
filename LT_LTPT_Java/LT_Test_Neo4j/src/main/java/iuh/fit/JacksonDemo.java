package iuh.fit;

import com.fasterxml.jackson.databind.ObjectMapper;
import iuh.fit.core.entity.Student;

import java.util.Map;
import java.util.Objects;

public class JacksonDemo {
    public static void main(String[] args) {
        Student student = Student.builder()
                .id("23634741")
                .name("Nhat Khanh")
                .gpa(3)
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.convertValue(student, Map.class);
        System.out.println(map);
    }
}
