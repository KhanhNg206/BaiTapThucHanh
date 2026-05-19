package dto;

import entity.AppointmentId;
import entity.Doctor;
import entity.Patient;
import entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppointmentDTO {
    private AppointmentId id;
    private Status status;
    private String doctorName;
    private String patientName;
    private Doctor doctor;
    private Patient patient;
}
