package entity;

import lombok.*;
import java.util.HashSet;
import java.util.Set;

public class Course {

    private Long id;
    private String name;
    private int credits;
    private Set<Enrollment> enrollments = new HashSet<>();

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder

    public class Enrollment {
        private Long id;
        private String name;
        private int credits;
        private Set<Student> students = new HashSet<>();
    }
}
