package com.helloevents.service;

import com.helloevents.dto.EventRequest;
import com.helloevents.dto.EventResponse;
import com.helloevents.model.Event;
import com.helloevents.model.User;
import com.helloevents.repository.EventRepository;
import com.helloevents.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;


    public EventResponse createEvent(EventRequest eventRequest ,Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("User not found with id: " + userId);
        }
        Event event = new Event();
        event.setTitle(eventRequest.getTitle());
        event.setLocation(eventRequest.getLocation());
        event.setDate(eventRequest.getDate());
        event.setPrice(eventRequest.getPrice());
        event.setAvailableSeats(eventRequest.getAvailableSeats());
        event.setUser(userOpt.get());

        Event savedEvent = eventRepository.save(event);
        return new EventResponse(savedEvent);
    }
    public EventResponse updateEvent(Long id, EventRequest eventRequest) {
        Optional<Event> eventOpt = eventRepository.findById(id);
        if (!eventOpt.isPresent()) {
            return null;
        }

        Event event = eventOpt.get();
        event.setTitle(eventRequest.getTitle());
        event.setLocation(eventRequest.getLocation());
        event.setDate(eventRequest.getDate());
        event.setPrice(eventRequest.getPrice());
        event.setAvailableSeats(eventRequest.getAvailableSeats());

        Event updatedEvent = eventRepository.save(event);
        return new EventResponse(updatedEvent);
    }
    public boolean deleteEvent(Long id) {
        Optional<Event> eventOpt = eventRepository.findById(id);
        if (!eventOpt.isPresent()) {
            return false;
        }
        eventRepository.delete(eventOpt.get());
        return true;
    }
    public List<EventResponse> getAllEvents() {
        List<EventResponse> responses = new ArrayList<>();
        for (Event event : eventRepository.findAll()) {
            responses.add(new EventResponse(event));
        }
        return responses;
    }
    public EventResponse getEventById(Long id) {
        Optional<Event> eventOpt = eventRepository.findById(id);
        if (!eventOpt.isPresent()) {
            return null;
        }
        return new EventResponse(eventOpt.get());
    }

}
