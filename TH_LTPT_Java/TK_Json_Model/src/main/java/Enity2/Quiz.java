package Enity2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {
    private String id;
    private String name;
    private int score;
    private List<Questions> ques;
    private Category category;
}
