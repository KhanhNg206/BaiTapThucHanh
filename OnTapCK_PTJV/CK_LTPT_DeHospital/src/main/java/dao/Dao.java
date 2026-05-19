package dao;

import db.JPAUtils;
import entity.Appointment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;

public class Dao {

    public boolean addAppointment(Appointment appointment){
        EntityManager em = JPAUtils.getEntityManager();
        try{
            em.getTransaction().begin();

            Appointment appointment1 = em.find(Appointment.class,appointment.getId());
            if(appointment1 != null){
                return false;
            }

            em.persist(appointment);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            em.close();
        }
    }

    public List<Object[]> getAppointmentDetails(){
        EntityManager em = JPAUtils.getEntityManager();
        try{
            String jpql = """
                    select a.id, a.doctor.fullName, a.patient.fullName, a.status 
                    from Appointment a 
                    """;
            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            em.close();
        }
    }

    public List<Object[]> getDoctorWorkload(){
        EntityManager em = JPAUtils.getEntityManager();
        try{
            String jpql = """
                    select a.id.doctorId,a.doctor.fullName,a.id.appointmentTime,count(a.id.appointmentTime)
                    from Appointment a 
                    group by a.id.doctorId,
                             a.id.appointmentTime
                    having count(a) >= 2
                    """;
            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            em.close();
        }
    }
}
