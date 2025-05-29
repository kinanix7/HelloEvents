package com.helloevents.service;

import com.helloevents.dto.ReservationRequest;
import com.helloevents.dto.ReservationResponse;
import com.helloevents.model.Event;
import com.helloevents.model.Reservation;
import com.helloevents.model.User;
import com.helloevents.repository.EventRepository;
import com.helloevents.repository.ReservationRepository;
import com.helloevents.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;


    public List<ReservationResponse> getAllReservations(){
    return reservationRepository.findAll().stream()
        .map(ReservationResponse::new)
        .collect(Collectors.toList());
}



    public List<ReservationResponse> getReservationsByEventId(Long eventId) {
        return reservationRepository.findByEventId(eventId).stream()
                .map(ReservationResponse::new)
                .collect(Collectors.toList());
    }
    public ReservationResponse getReservationById(Long id) {
        Optional<Reservation> reservationOpt = reservationRepository.findById(id);
        return reservationOpt.map(ReservationResponse::new).orElse(null);
    }
    public boolean deleteReservation(Long id ){
        Optional<Reservation> reservationOpt = reservationRepository.findById(id);
        if (!reservationOpt.isPresent()) {
            return false;
        }
        reservationRepository.deleteById(id);
        return true;

    }
    public ReservationResponse createReservation(ReservationResponse reservationResponse, Long userId) {
        // à partir de reservationResponse, récupérer eventId et nombrePlaces
        Long eventId = reservationResponse.getEventId();
        int nombrePlaces = reservationResponse.getNombrePlaces();

        Optional<User> userOpt = userRepository.findById(userId);
        if (!userOpt.isPresent()) {
            return null;
        }

        Optional<Event> eventOpt = eventRepository.findById(eventId);
        if (!eventOpt.isPresent()) {
            return null;
        }

        Event event = eventOpt.get();

        if (event.getAvailableSeats() < nombrePlaces) {
            return null;
        }

        double totalPrice = event.getPrice() * nombrePlaces;

        Reservation reservation = new Reservation();
        reservation.setUser(userOpt.get());
        reservation.setEvent(event);
        reservation.setNombrePlaces(nombrePlaces);
        reservation.setTotalPrice(totalPrice);
        reservation.setReservationDate(LocalDate.now());
        reservation.setStatus(Reservation.Status.PENDING);

        event.setAvailableSeats(event.getAvailableSeats() - nombrePlaces);
        eventRepository.save(event);

        Reservation savedReservation = reservationRepository.save(reservation);
        return new ReservationResponse(savedReservation);
    }



}
