package iuh.fit.entity;

import lombok.*;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {
    private String questionId;
    private String text;
    private List<String> options;
    private String correctAnswer;
}
