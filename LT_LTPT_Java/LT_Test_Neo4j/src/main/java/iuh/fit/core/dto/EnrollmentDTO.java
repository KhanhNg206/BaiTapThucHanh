
package iuh.fit.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnrollmentDTO {
    @JsonProperty("student_id")
    private String studentId;
    @JsonProperty("course_id")
    private String courseId;
    private String semester;
    private double grade;
}

    