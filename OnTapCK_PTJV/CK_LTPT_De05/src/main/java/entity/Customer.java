package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {

    @Id
    @Column(name = "customer_id")
    private String id;
    private String name;

    @Column(name = "year_of_birth")
    private int yearOfBirth;
    private String phone;
    private String address;

    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    private Set<Ticket> tickets;
}
