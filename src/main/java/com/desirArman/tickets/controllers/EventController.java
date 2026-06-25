package com.desirArman.tickets.controllers;

import com.desirArman.tickets.domain.CreateEventRequest;
import com.desirArman.tickets.domain.dtos.CreateEventRequestDto;
import com.desirArman.tickets.domain.dtos.CreateEventResponseDto;
import com.desirArman.tickets.domain.dtos.ListEventResponseDto;
import com.desirArman.tickets.domain.entities.Event;
import com.desirArman.tickets.mappers.EventMapper;
import com.desirArman.tickets.services.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventMapper eventMapper;
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<CreateEventResponseDto> createEvent(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateEventRequestDto createEventRequestDto){

        CreateEventRequest createEventRequest = eventMapper.fromDto(createEventRequestDto);
        UUID userId = parseUserId(jwt);

        Event createdEvent = eventService.createEvent(userId, createEventRequest);
        CreateEventResponseDto createEventResponseDto = eventMapper.toDto(createdEvent);

        return new ResponseEntity<>(createEventResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ListEventResponseDto>> listEvents (
            @AuthenticationPrincipal Jwt jwt,
            Pageable pageable){
        UUID organizerId = parseUserId(jwt);
        Page<Event> eventsList = eventService.listEventsforOrganizer(organizerId, pageable);
        Page<ListEventResponseDto> finalEvents = eventsList.map(event -> eventMapper.toListEventResponseDto(event));

        return  ResponseEntity.ok(finalEvents);

    }

    private UUID parseUserId(Jwt jwt){
        return  UUID.fromString(jwt.getSubject());
    }
}