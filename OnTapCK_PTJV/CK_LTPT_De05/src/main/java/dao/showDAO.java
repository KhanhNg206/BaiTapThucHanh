package dao;

import db.JPAUtils;
import entity.Movie;
import entity.Show;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class showDAO {

    public List<Show> listShowByCurrentDateAndDirector(String director){
        try(EntityManager em = JPAUtils.getEntityManager()){
            LocalDateTime currenDay = LocalDate.now().atStartOfDay();
            LocalDateTime nextDay = currenDay.plusDays(1);

            String jpql = """
                       select s
                       from Show s join s.movie m
                       where m.director = :director
                             and s.showDateTime >= :currenDay 
                             and s.showDateTime < :nextDay
                    """;

            TypedQuery<Show> query = em.createQuery(jpql,Show.class);
            query.setParameter("director",director);
            query.setParameter("currenDay",currenDay);
            query.setParameter("nextDay",nextDay);
            return query.getResultList();

        }
    }

    public boolean updateShowDateTime(String showId, LocalDateTime newShowDateTime) {
        try (EntityManager em = JPAUtils.getEntityManager()) {
            em.getTransaction().begin();
            Show show = em.find(Show.class,showId);
            if(show == null){
                em.getTransaction().rollback();
                return false;
            }
            String jpql = """
                select count(t)
                from Ticket t
                join t.show s
                where s.id = :showId
                """;

           Long count = em.createQuery(jpql, Long.class)
                   .setParameter("showId",showId)
                   .getSingleResult();
           if(count != 0){
               em.getTransaction().rollback();
               return false;
           }
           show.setShowDateTime(newShowDateTime);
           em.merge(show);
           em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addMovie(Movie movie){
        try(EntityManager em = JPAUtils.getEntityManager()){
            em.getTransaction().begin();
            if(!movie.getId().matches("M\\d{3}")){
                em.getTransaction().rollback();
                return false;
            }

            if(movie.getDuration() <= 0){
                em.getTransaction().rollback();
                return false;
            }

            em.merge(movie);
            em.getTransaction().commit();
            return true;
        }
    }
}
