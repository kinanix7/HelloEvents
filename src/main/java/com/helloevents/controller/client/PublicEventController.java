package com.helloevents.controller.client;

import com.helloevents.dto.EventResponse;
import com.helloevents.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Contrôleur pour gérer les opérations publiques liées aux événements
 * Ce contrôleur permet aux utilisateurs non authentifiés de consulter les événements
 */
@RestController
@RequestMapping("/api/events/public")
@CrossOrigin("*")

public class PublicEventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EventResponse getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

}
