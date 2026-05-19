package dto;

import entity.Level;
import entity.Type;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO implements Serializable {
    private String id;
    private Type type;
    private Level questionLevel;
    private String questionText;
}
