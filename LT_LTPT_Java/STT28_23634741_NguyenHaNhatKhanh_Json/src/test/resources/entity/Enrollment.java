package entity;

import lombok.*;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class Enrollment {
    private Student student;
    private Course course;
    private Double grade;

  

    public static class Student {
        private Student student;
        private Course course;
        private Double grade;
    }
}
