package com.helloevents.dto;

import com.helloevents.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponse {

    private Long id;
    private Double totalPrice;
    private Integer nombrePlaces;
    private LocalDate reservationDate;
    private String status;
    private Long eventId;
    private String eventTitle;
    private String username;


    public ReservationResponse(Reservation reservation) {
        this.id = reservation.getId();
        this.totalPrice = reservation.getTotalPrice();
        this.nombrePlaces = reservation.getNombrePlaces();
        this.reservationDate = reservation.getReservationDate();
        this.status = reservation.getStatus().name();
        this.eventId = reservation.getEvent().getId();
        this.eventTitle = reservation.getEvent().getTitle();
        this.username = reservation.getUser().getUsername();
    }
}
