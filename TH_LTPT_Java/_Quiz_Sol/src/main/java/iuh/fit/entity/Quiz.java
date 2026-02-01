package iuh.fit.entity;

import lombok.*;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quiz {

    private String quizId;
    private String name;
    private int score;
    private List<Question> questions;
    private Category category;
}
