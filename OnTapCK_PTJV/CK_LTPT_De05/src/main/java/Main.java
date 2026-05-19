import dao.showDAO;
import dto.movieDTO;
import entity.Movie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import service.impl.showServiceImpl;
import service.showService;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        showDAO showDAO = new showDAO();
        showService service = new showServiceImpl();

//        service.listShowByCurrentDateAndDirector("The Wachowskis").forEach(System.out::println);
//        System.out.println(service.updateShowDateTime("s016", LocalDateTime.now().minusDays(5)));
        movieDTO movie = new movieDTO();
        movie.setId("M010");
        movie.setTitle("new title");
        movie.setDirector("new director");
        movie.setGenre("new genre");
        movie.setReleaseYear(2005);
        movie.setDuration(200);
        movie.setShows(null);
        System.out.println(service.addMovie(movie));
    }
}
