package com.helloevents.controller.admin;

import com.helloevents.dto.ReservationResponse;
import com.helloevents.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/reservations")
@PreAuthorize("hasRole('ADMIN')")
public class AdminReservationController {
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
}
