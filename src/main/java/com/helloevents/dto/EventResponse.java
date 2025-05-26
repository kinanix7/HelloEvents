package com.helloevents.dto;

import com.helloevents.model.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventResponse {

    private Long id;
    private String title;
    private String location;
    private LocalDate date;
    private Double price;
    private Integer availableSeats;
    private String creatorUsername;

    public EventResponse(Event event) {
        this.id = event.getId();
        this.title = event.getTitle();
        this.location = event.getLocation();
        this.date = event.getDate();
        this.price = event.getPrice();
        this.availableSeats = event.getAvailableSeats();
        this.creatorUsername = event.getUser().getUsername();
    }
}
