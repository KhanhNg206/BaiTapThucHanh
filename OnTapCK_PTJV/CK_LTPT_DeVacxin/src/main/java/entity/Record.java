package entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name = "records")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class Record implements Serializable {

    @Id
    @Column(name = "record_id")
    private Long id;

    @Column(name = "injection_date")
    private LocalDate injectionDate;

    @Column(name = "dose_number")
    private int doseNumber;

    @Enumerated(EnumType.STRING)
    private DoseStatus status;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @ToString.Exclude
    private Person personsss;

    @ManyToOne
    @JoinColumn(name = "vaccine_id")
    @ToString.Exclude
    private Vaccine vaccine;
}
