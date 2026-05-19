package models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "people")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;
    private String email;

    @Column(name = "professional_title")
    private String professionalTitle;

    @OneToMany(mappedBy = "person")
    @ToString.Exclude
    private Set<Reviews> reviews;

}
