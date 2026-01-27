package entity;

import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Student {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private boolean active;
    private List<String> phones;
    private Set<Enrollment> enrollments = new HashSet<>();
}
