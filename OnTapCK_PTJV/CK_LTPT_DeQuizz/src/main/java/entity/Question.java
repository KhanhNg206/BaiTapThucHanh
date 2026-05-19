package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

@Table(name = "questions")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question implements Serializable {

    @Id
    @Column(name = "question_id")
    private String id;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "question_level")
    @Enumerated(EnumType.STRING)
    private Level questionLevel;

    @Column(name = "question_text")
    private String questionText;

    @OneToMany(mappedBy = "questions")
    @ToString.Exclude
    private Set<Answer> answers;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    private Category category;

    @ManyToMany(mappedBy = "questions")
    @ToString.Exclude
    private Set<Quiz> quizzes;
}
