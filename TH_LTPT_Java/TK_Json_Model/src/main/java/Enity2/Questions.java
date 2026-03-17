package Enity2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Questions {
    private String quesId;
    private String text;
    private List<String> options;
    private String correct;
}
