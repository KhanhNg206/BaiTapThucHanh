package mapper;

import dto.AppointmentDTO;
import entity.Appointment;
import entity.AppointmentId;
import entity.Doctor;
import entity.Patient;

public class AppointmentMapper {
    public static Appointment dtoToAppEntity(AppointmentDTO appointmentDTO){
       AppointmentId appointmentId = new AppointmentId();
       appointmentId.setDoctorId(appointmentDTO.getId().getDoctorId());
       appointmentId.setPatientId(appointmentDTO.getId().getPatientId());
       appointmentId.setAppointmentTime(appointmentDTO.getId().getAppointmentTime());

        Appointment appointment = new Appointment();
        appointment.setId(appointmentId);
        appointment.setStatus(appointmentDTO.getStatus());
        appointment.setDoctor(appointmentDTO.getDoctor());
        appointment.setPatient(appointmentDTO.getPatient());
        return appointment;
    }
}
