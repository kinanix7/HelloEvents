package com.helloevents.repository;

import com.helloevents.model.Reservation;
import org.springframework.boot.autoconfigure.amqp.RabbitStreamTemplateConfigurer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserId(Long userId);
    List<Reservation> findByEventId(Long eventId);

    @Query(value = "SELECT COUNT(*) FROM reservations WHERE event_id=?",nativeQuery = true)
    long countReservationByEventId(Long eventId);



}
