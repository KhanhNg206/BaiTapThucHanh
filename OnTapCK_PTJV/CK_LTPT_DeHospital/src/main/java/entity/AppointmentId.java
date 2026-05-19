package entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class AppointmentId implements Serializable {
    private String doctorId;
    private String patientId;
    private LocalDateTime appointmentTime;
}
