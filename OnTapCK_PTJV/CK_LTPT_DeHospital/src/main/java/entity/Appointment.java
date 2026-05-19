package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Appointments")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Appointment {

    @EmbeddedId
    private AppointmentId id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @MapsId("doctorId")
    @JoinColumn(name = "doctorId")
    @ToString.Exclude
    private Doctor doctor;

    @ManyToOne
    @MapsId("patientId")
    @JoinColumn(name = "patientId")
    @ToString.Exclude
    private Patient patient;
}
