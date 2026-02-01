package iuh.fit.entity;


import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Course {

    private String id;
    private String name;
    private int credits;

//    private Set<Enrollment> enrollments = new HashSet<>();


}
