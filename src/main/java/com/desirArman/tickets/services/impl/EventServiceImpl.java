package com.desirArman.tickets.services.impl;

import com.desirArman.tickets.domain.CreateEventRequest;
import com.desirArman.tickets.domain.entities.Event;
import com.desirArman.tickets.domain.entities.TicketType;
import com.desirArman.tickets.domain.entities.User;
import com.desirArman.tickets.exceptions.UserNotFoundException;
import com.desirArman.tickets.repositories.EventRepository;
import com.desirArman.tickets.repositories.UserRepository;
import com.desirArman.tickets.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    @Override
    public Event createEvent(UUID organizerId, CreateEventRequest eventRequest) {

        User organizer = userRepository.findById(organizerId)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User with ID '%s' not found", organizerId)));

        Event eventToCreate = new Event();
        eventToCreate.setName(eventRequest.getName());
        eventToCreate.setVenue(eventRequest.getVenue());
        eventToCreate.setStart(eventRequest.getStart());
        eventToCreate.setEnd(eventRequest.getEnd());
        eventToCreate.setSaleStart(eventRequest.getSalesStart());
        eventToCreate.setSaleEnd(eventRequest.getSalesEnd());
        eventToCreate.setStatus(eventRequest.getStatus());
        eventToCreate.setOrganizer(organizer);

        List<TicketType> ticketTypeList = eventRequest.getTicketTypes().stream().map(
                ticketType -> {

                    TicketType ticketTypeToCreate = new TicketType();
                    ticketTypeToCreate.setName(ticketType.getName());
                    ticketTypeToCreate.setPrice(ticketType.getPrice());
                    ticketTypeToCreate.setDescription(ticketType.getDescription());
                    ticketTypeToCreate.setTotalAvailable(ticketType.getTotalAvailable());
                    ticketTypeToCreate.setEvent(eventToCreate);

                    return ticketTypeToCreate;
                }).toList();
        eventToCreate.setTicketTypes(ticketTypeList);
        return eventRepository.save(eventToCreate);
    }

    @Override
    public Page<Event> listEventsforOrganizer(UUID organizerId, Pageable pageable) {
        return eventRepository.findByOrganizerId(organizerId, pageable);
    }
}