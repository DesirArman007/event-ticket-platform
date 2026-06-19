package com.desirArman.tickets.services;


import com.desirArman.tickets.domain.CreateEventRequest;
import com.desirArman.tickets.domain.entities.Event;

import java.util.UUID;

public interface EventService {

    Event createEvent(UUID organizerId, CreateEventRequest eventRequest);


}
