package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import models.Book;
import models.Person;
import models.Reviews;
import db.JPAUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class bookDAO {
    public List<Book> listRateBooks(String author,int rating){
        try(EntityManager em = JPAUtils.getEntityManager()){
            String jpql = """
                      SELECT b
                       FROM Book b
                       JOIN b.reviews r
                       join b.authors a
                       where a = :author and r.rating >= :rating
                       """;
            TypedQuery<Book> query = em.createQuery(jpql , Book.class);
            query.setParameter("author",author);
            query.setParameter("rating",rating);

            return query.getResultList();
        }
    }

    public Map<String,Long> countBooksByAuthor() {
        try(EntityManager em = JPAUtils.getEntityManager()){
            String jpql = """
                    select a,count (bt)
                    from BookTranslation bt
                    JOIN bt.authors a
                    group by a
                    order by a
                    """;

            TypedQuery<Object[]> query = em.createQuery(jpql,Object[].class);

            return query.getResultList()
                    .stream()
                    .collect(Collectors.toMap(
                            obj -> (String) obj[0],
                            obj -> (Long) obj[1],
                            (v1 , v2) -> v1,
                            LinkedHashMap::new
                    ));
        }
    }

    public boolean updateReviews(String isbn,String readerID,int rating,String comment){
        try(EntityManager em = JPAUtils.getEntityManager()){
            em.getTransaction().begin();

            Book book = em.find(Book.class , isbn);
            if(book == null) return false;

            Person person = em.find(Person.class , readerID);
            if(person == null) return false;

            if(rating < 1 || rating > 5) return false;

            if(comment == null || comment.equals("")) return false;

            Reviews reviews = new Reviews(book,person,rating,comment);

            em.persist(reviews);

            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
