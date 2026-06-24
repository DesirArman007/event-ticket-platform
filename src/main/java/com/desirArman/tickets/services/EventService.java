package com.desirArman.tickets.services;


import com.desirArman.tickets.domain.CreateEventRequest;
import com.desirArman.tickets.domain.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface EventService {

    Event createEvent(UUID organizerId, CreateEventRequest eventRequest);

    Page<Event> listEventsforOrganizer(UUID organizerId, Pageable pageable);
}
