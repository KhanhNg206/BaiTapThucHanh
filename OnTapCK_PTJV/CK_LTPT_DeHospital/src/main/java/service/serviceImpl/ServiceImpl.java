package service.serviceImpl;

import dao.Dao;
import dto.AppointmentDTO;
import entity.Appointment;
import mapper.AppointmentMapper;

import java.util.List;

public class ServiceImpl implements service.Service {
    private Dao dao;

    public ServiceImpl() {
        this.dao = new Dao();
    }

    @Override
    public boolean addAppointment(AppointmentDTO appointmentDTO){
        return dao.addAppointment(AppointmentMapper.dtoToAppEntity(appointmentDTO));
    }

    @Override
    public List<Object[]> getAppointmentDetails(){
        return  dao.getAppointmentDetails();
    }

    @Override
    public List<Object[]> getDoctorWorkload(){
        return dao.getDoctorWorkload();
    }
}
