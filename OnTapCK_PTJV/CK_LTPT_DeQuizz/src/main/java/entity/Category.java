package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {

    @Id
    @Column(name = "category_id")
    private String id;

    @Column(name = "category_name")
    private String name;
    private String description;

    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    private Set<Question> quess;

    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    private Set<Quiz> quizzes;

}
