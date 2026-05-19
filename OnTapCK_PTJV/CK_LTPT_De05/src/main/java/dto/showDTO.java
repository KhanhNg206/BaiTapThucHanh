package dto;

import entity.Movie;
import entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class showDTO implements Serializable {
    private String id;
    private LocalDateTime showDateTime;
    private String hallName;
    private Movie movie;
//    private Set<Ticket> tickets;
}
