package models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "reviews")
@IdClass(ReviewId.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Reviews implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "ISBN")
    @EqualsAndHashCode.Include
    private Book book;

    @Id
    @ManyToOne
    @JoinColumn(name = "person_id")
    @ToString.Exclude
    private Person person;

    private int rating;
    private String comment;
}
