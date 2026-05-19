package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "People")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {

    @Id
    @Column(name = "personId")
    protected String id;
    protected String fullName;
    protected String email;
}
