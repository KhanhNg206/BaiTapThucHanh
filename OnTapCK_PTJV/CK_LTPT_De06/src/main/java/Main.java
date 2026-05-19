import daos.bookDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import models.Book;

import java.util.List;

public class Main {
    public static void main(String[] args) {

//        class này chỉ dùng để test
//       EntityManager em = Persistence.createEntityManagerFactory("mariadb")
//                .createEntityManager();

        bookDAO bookDAO = new bookDAO();
//        bookDAO.listRateBooks("Brian W. Kernighan",6).forEach(System.out::println);

//        bookDAO.countBooksByAuthor().forEach((k,v) -> System.out.println(k + ": " + v));

         System.out.println(bookDAO.updateReviews("9780134685991","4",4,"Comment mới 2"));


    }
}
