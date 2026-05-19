import dao.Dao;
import dto.AppointmentDTO;
import entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import lombok.ToString;
import service.Service;
import service.serviceImpl.ServiceImpl;

import javax.print.Doc;
import javax.swing.text.html.parser.Entity;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        EntityManager em = Persistence.createEntityManagerFactory("mariadb")
//                .createEntityManager();
        Dao dao = new Dao();
        Service service = new ServiceImpl();

        Doctor doctor = new Doctor();
        Patient patient = new Patient();
        doctor.setId("D11");
        patient.setId("P11");
        AppointmentId appointmentId = new AppointmentId();
        appointmentId.setDoctorId(doctor.getId());
        appointmentId.setPatientId(patient.getId());
        appointmentId.setAppointmentTime(LocalDateTime.now());

        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setId(appointmentId);
        appointmentDTO.setDoctor(doctor);
        appointmentDTO.setPatient(patient);
        appointmentDTO.setStatus(Status.CANCELLED);

        System.out.println(service.addAppointment(appointmentDTO));

//        List<Object[]> list = dao.getAppointmentDetails();
//        for (Object[] obj : list){
//            System.out.println(Arrays.toString(obj));
//        }

//        List<Object[]> list = dao.getDoctorWorkload();
//        for (Object[] obj : list){
//            System.out.println(Arrays.toString(obj));
//        }
    }
}
