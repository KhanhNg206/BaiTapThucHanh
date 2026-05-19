package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket implements Serializable {

    @Id
    @Column(name = "ticket_number")
    private String ticketNumber;
    private String seat;

    @Enumerated(EnumType.STRING)
    private Type type;
    private double price;

    @Column(name = "booking_date")
    private LocalDate bookingDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @ToString.Exclude
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "show_id")
    @ToString.Exclude
    private Show show;
}
