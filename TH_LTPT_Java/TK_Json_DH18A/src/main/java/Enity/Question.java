package Enity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Question {
    private String quesId;
    private String text;
    private List<String> options;
    private String correct_answer;
}
