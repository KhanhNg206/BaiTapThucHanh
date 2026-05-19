package service;

import dto.AppointmentDTO;

import java.util.List;

public interface Service {
    boolean addAppointment(AppointmentDTO appointmentDTO);

    List<Object[]> getAppointmentDetails();

    List<Object[]> getDoctorWorkload();
}
