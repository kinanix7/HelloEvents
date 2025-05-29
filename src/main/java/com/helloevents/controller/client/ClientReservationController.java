package com.helloevents.controller.client;

import com.helloevents.dto.ReservationRequest;
import com.helloevents.dto.ReservationResponse;
import com.helloevents.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client/reservations")
@RequiredArgsConstructor
@CrossOrigin("*")

public class ClientReservationController {


    @Autowired
    private ReservationService reservationService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReservationResponse> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReservationResponse getReservationById(@PathVariable Long id) {
        return reservationService.getReservationById(id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return "Réservation supprimée avec succès";
    }
    @PostMapping("/create")
    public ResponseEntity<ReservationResponse> createReservation(@RequestBody ReservationResponse reservationResponse) {

        Long userId = extractUserIdFromReservationResponse(reservationResponse); // méthode à créer ou extraire autrement
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        ReservationResponse response = reservationService.createReservation(reservationResponse, userId);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    private Long extractUserIdFromReservationResponse(ReservationResponse reservationResponse) {
        return null;
    }

}
