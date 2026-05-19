package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "persons")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Person implements Serializable {

    @Id
    @Column(name = "person_id")
    protected String id;

    @Column(name = "full_name")
    protected String fullName;
    protected LocalDate dob;
    protected String gender;
    protected double height;
    protected double weight;

    @OneToMany(mappedBy = "personsss")
    @ToString.Exclude
    private Set<Record> records;

}
