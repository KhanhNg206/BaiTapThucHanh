package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;


@Table(name = "Patients")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@PrimaryKeyJoinColumn(name = "patientId")
@ToString(callSuper = true)
public class Patient extends Person{
    private String address;

    @ElementCollection
    @CollectionTable(
            name = "Phones",
            joinColumns = @JoinColumn(name = "patientId")
    )
    @Column(name = "phoneNumber")
    @ToString.Exclude
    private Set<String> phones;

    @OneToMany(mappedBy = "patient")
    @ToString.Exclude
    private Set<Appointment> appointments;
}
