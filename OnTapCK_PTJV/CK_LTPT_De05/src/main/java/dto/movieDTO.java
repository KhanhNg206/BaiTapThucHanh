package dto;

import entity.Show;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class movieDTO implements Serializable {
    private String id;
    private String title;
    private String genre;
    private int releaseYear;
    private String director;
    private int duration;
    private Set<String> actors;
    private Set<Show> shows;
}
