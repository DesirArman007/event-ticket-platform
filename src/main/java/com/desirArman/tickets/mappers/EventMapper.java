package com.desirArman.tickets.mappers;

<<<<<<< Updated upstream
public interface EventMapper {
=======
import com.desirArman.tickets.domain.CreateEventRequest;
import com.desirArman.tickets.domain.CreateTicketTypeRequest;
import com.desirArman.tickets.domain.dtos.CreateEventRequestDto;
import com.desirArman.tickets.domain.dtos.CreateEventResponseDto;
import com.desirArman.tickets.domain.dtos.CreateTicketTypeRequestDto;
import com.desirArman.tickets.domain.entities.Event;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper( componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {


    CreateTicketTypeRequest fromDto (CreateTicketTypeRequestDto dto);

    CreateEventRequest fromDto (CreateEventRequestDto dto);

    CreateEventResponseDto toDto (Event event);



>>>>>>> Stashed changes
}
