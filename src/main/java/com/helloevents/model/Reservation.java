package com.helloevents.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "reservations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double totalPrice;
    private Integer nombrePlaces;
    private LocalDate reservationDate;
    private Status status;

    // Relation avec User (client qui fait la réservation)
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Relation avec Event (événement réservé)
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    public enum Status {
        PENDING, CONFIRMED, CANCELLED
    }
}
