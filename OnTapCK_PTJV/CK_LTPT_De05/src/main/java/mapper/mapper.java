package mapper;

import dto.movieDTO;
import dto.showDTO;
import entity.Movie;
import entity.Show;

public class mapper {
    public static showDTO showToDTO(Show show){
        if(show == null){
            return null;
        }
        showDTO newDTO = new showDTO();
        newDTO.setId(show.getId());
        newDTO.setMovie(show.getMovie());
//        newDTO.setTickets(show.getTickets());
        newDTO.setShowDateTime(show.getShowDateTime());
        newDTO.setHallName(show.getHallName());
        return newDTO;
    }

    public static Movie DTOtoMovie(movieDTO dto){
        if(dto == null){
            return null;
        }
        Movie newMovie = new Movie();
        newMovie.setId(dto.getId());
        newMovie.setDirector(dto.getDirector());
        newMovie.setActors(dto.getActors());
        newMovie.setGenre(dto.getGenre());
        newMovie.setShows(dto.getShows());
        newMovie.setDuration(dto.getDuration());
        newMovie.setReleaseYear(dto.getReleaseYear());
        newMovie.setTitle(dto.getTitle());
        return newMovie;
    }

//    public static movieDTO movieToDTO(Movie movie){
//        if(movie == null){
//            return null;
//        }
//        movieDTO newDTO = new movieDTO();
//        newDTO.setId(movie.getId());
//        newDTO.setDirector(movie.getDirector());
//        newDTO.setActors(movie.getActors());
//        newDTO.setGenre(movie.getGenre());
//        newDTO.setShows(movie.getShows());
//        newDTO.setDuration(movie.getDuration());
//        newDTO.setReleaseYear(movie.getReleaseYear());
//        newDTO.setTitle(movie.getTitle());
//        return newDTO;
//    }
}
