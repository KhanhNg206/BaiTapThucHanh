package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

@Table(name = "answers")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer implements Serializable {

    @Id
    @Column(name = "answer_id")
    private String id;

    @Column(name = "answer_text")
    private String aswerText;

    @Column(name = "is_correct")
    private boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "question_id")
    @ToString.Exclude
    private Question questions;
}
