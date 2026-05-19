package entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "vaccines")
@Entity

@EqualsAndHashCode(exclude = "records")
public class Vaccine implements Serializable {

    @Id
    @Column(name = "vaccine_id")
    private String id;

    @Column(name = "vaccine_name")
    private String name;
    private String manufacturer;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @OneToMany(mappedBy = "vaccine")
    @ToString.Exclude
    private Set<Record> records;
}
