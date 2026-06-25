package com.desirArman.tickets.mappers;

import com.desirArman.tickets.domain.CreateEventRequest;
import com.desirArman.tickets.domain.CreateTicketTypeRequest;
import com.desirArman.tickets.domain.dtos.*;
import com.desirArman.tickets.domain.entities.Event;
import com.desirArman.tickets.domain.entities.TicketType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper( componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    CreateTicketTypeRequest fromDto (CreateTicketTypeRequestDto dto);

    CreateEventRequest fromDto (CreateEventRequestDto dto);

    CreateEventResponseDto toDto (Event event);

    ListEventTicketTypeResponseDto toDto (TicketType ticketType);

    ListEventResponseDto toListEventResponseDto (Event event);

}