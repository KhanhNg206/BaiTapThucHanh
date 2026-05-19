package service;

import dto.movieDTO;
import dto.showDTO;
import entity.Movie;
import entity.Show;

import java.time.LocalDateTime;
import java.util.List;

public interface showService {
    List<showDTO> listShowByCurrentDateAndDirector(String director);

    boolean updateShowDateTime(String showId, LocalDateTime newShowDateTime);

    boolean addMovie(movieDTO movie);
}
