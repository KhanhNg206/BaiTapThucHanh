package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "shows")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Show implements Serializable {
    @Id
    @Column(name = "show_id")
    private String id;

    @Column(name = "show_date_time")
    private LocalDateTime showDateTime;

    @Column(name = "hall_name")
    private String hallName;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @OneToMany(mappedBy = "show")
    @ToString.Exclude
    private Set<Ticket> tickets;
}
