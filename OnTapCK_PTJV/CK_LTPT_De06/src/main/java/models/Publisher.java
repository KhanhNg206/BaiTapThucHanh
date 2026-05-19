package models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "publishers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Publisher implements Serializable {

    @Id
    @Column(name = "publisher_id")
    private String id;
    private String name;
    private String address;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "publisher")
    @ToString.Exclude
    private Set<Book> books;

}
