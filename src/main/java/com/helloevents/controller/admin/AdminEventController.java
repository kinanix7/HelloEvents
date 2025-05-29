package com.helloevents.controller.admin;

import com.helloevents.dto.EventRequest;
import com.helloevents.dto.EventResponse;
import com.helloevents.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/events")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin("*")

public class AdminEventController {
    @Autowired
    private EventService eventService;
    @Autowired
    private com.helloevents.repository.UserRepository userRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EventResponse> getAllEvents() {
        return eventService.getAllEvents();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EventResponse getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventResponse createEvent(@RequestBody EventRequest eventRequest) {
        Long userId = getCurrentUserId();
        return eventService.createEvent(eventRequest, userId);
    }
    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername()).get().getId();
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EventResponse updateEvent(@PathVariable Long id, @RequestBody EventRequest eventRequest) {
        return eventService.updateEvent(id, eventRequest);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "Événement supprimé avec succès";
    }


}
