package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie implements Serializable {

    @Id
    @Column(name = "movie_id")
    private String id;
    private String title;
    private String genre;

    @Column(name = "release_year")
    private int releaseYear;
    private String director;
    private int duration;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "movie_actors",
            joinColumns = @JoinColumn(name = "movie_id")
    )
    @Column(name = "actor", nullable = false)
    private Set<String> actors;

    @OneToMany(mappedBy = "movie")
    @ToString.Exclude
    private Set<Show> shows;
}
