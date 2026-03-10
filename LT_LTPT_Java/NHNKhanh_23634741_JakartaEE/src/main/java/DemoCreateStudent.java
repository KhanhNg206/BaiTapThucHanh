import core.entity.Student;
import infrastructure.db.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;

public class DemoCreateStudent {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        Student student =
                Student.builder().mssv("23700901").ho("Do").ten("Quan").gioiTinh("Nam")
                        .ngaySinh(LocalDate.of(2005,3,8)).build();
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            em.persist(student);
            tr.commit();
        } catch (Exception e){
            tr.rollback();
            e.printStackTrace();
        }
    }
}