package com.helloevents.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventRequest {

    private String title;
    private String location;
    private LocalDate date;
    private Double price;
    private Integer availableSeats;
}
