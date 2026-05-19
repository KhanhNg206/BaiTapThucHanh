package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "Doctors")
@NoArgsConstructor
@AllArgsConstructor
@Data
@PrimaryKeyJoinColumn(name = "doctorId")
@ToString(callSuper = true)
public class Doctor extends Person {

    private String specialty;
    private String hospital;

    @OneToMany(mappedBy = "doctor")
    @ToString.Exclude
    private Set<Appointment> appointments;
}
