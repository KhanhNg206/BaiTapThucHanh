package Enity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Quiz {
    private String quizId;
    private String name;
    private int score;
    private List<Question> question;
    private Category category;
}
