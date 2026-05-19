package network;

import dto.AppointmentDTO;
import entity.AppointmentId;
import entity.Doctor;
import entity.Patient;
import entity.Status;
import service.Service;
import service.serviceImpl.ServiceImpl;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;

public class ClientHandler implements Runnable{
    private Socket socket;
    private Service service;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        this.service = new ServiceImpl();
    }

    @Override
    public void run() {
        try(
                DataInputStream in = new DataInputStream(socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ){
            while(true){
                String command = in.readUTF();
                switch (command){
                    case "cauA" -> {
                        String doctorId = in.readUTF();
                        String patietId = in.readUTF();

                        Doctor doctor = new Doctor();
                        Patient patient = new Patient();
                        doctor.setId(doctorId);
                        patient.setId(patietId);
                        AppointmentId appointmentId = new AppointmentId();
                        appointmentId.setDoctorId(doctor.getId());
                        appointmentId.setPatientId(patient.getId());
                        appointmentId.setAppointmentTime(LocalDateTime.now());

                        AppointmentDTO appointmentDTO = new AppointmentDTO();
                        appointmentDTO.setId(appointmentId);
                        appointmentDTO.setDoctor(doctor);
                        appointmentDTO.setPatient(patient);
                        appointmentDTO.setStatus(Status.CANCELLED);

                        out.writeObject(service.addAppointment(appointmentDTO));
                        out.flush();
                    }
                    case "cauB" -> {
                        out.writeObject(service.getAppointmentDetails());
                        out.flush();
                    }
                    case "cauC" -> {
                        out.writeObject(service.getDoctorWorkload());
                        out.flush();
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
