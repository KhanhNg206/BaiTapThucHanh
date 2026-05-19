package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

@Table(name = "quizzes")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quiz implements Serializable {

    @Id
    @Column(name = "quiz_id")
    private String id;
    private String title;
    private int score;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @ToString.Exclude
    @JoinTable(
            name = "quizzes_questions",
            joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private Set<Question> questions;
}
