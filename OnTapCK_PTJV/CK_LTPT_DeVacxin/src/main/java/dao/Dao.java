package dao;

import db.JPAUtils;
import entity.DoseStatus;
import entity.Person;
import entity.Record;
import entity.Vaccine;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Dao {
    public boolean createNewRecord(Record record){
        try(EntityManager em = JPAUtils.getEntityManager()){
            em.getTransaction().begin();

            if(record.getDoseNumber() <= 0){
                return false;
            }
            if(record.getInjectionDate().isBefore(LocalDate.now())){
                return false;
            }
            em.persist(record);
            em.getTransaction().commit();
            return true;
        }
    }

    public boolean updateScheduledRecord(Long recordId, DoseStatus doseStatus){
        try(EntityManager em = JPAUtils.getEntityManager()){
            em.getTransaction().begin();

            Record record = em.find(Record.class,recordId);
            if(record.getStatus() != DoseStatus.SCHEDULED){
                return false;
            }

            record.setStatus(doseStatus);
            em.merge(record);
            em.getTransaction().commit();
            return true;
        }
    }

    public List<Person> listObesePeople(){
        try(EntityManager em = JPAUtils.getEntityManager()){
            String jpql = """
                    select p
                    from Person p 
                    where (p.weight / ( p.weight * p.weight)) >= 25
                    """;
            TypedQuery<Person> query = em.createQuery(jpql, Person.class);
            return query.getResultList();
        }
    }

    public Map<Vaccine,Integer> countRecordsByVaccines(){
        try(EntityManager em  = JPAUtils.getEntityManager()){
            DoseStatus status = DoseStatus.COMPLETED;
            String jpql = """
                    select v,count(v)
                    from Vaccine v 
                    join v.records r 
                    where r.status = :status
                    """;
            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            query.setParameter("status",status);
            return query.getResultList()
                    .stream()
                    .collect(Collectors.toMap(
                            obj -> (Vaccine) obj[0],
                            obj -> ((Long) obj[1]).intValue(),
                            (v1,v2) -> v1,
                            LinkedHashMap::new
                    ));
        }
    }


}
