package com.desirArman.tickets.domain.dtos;

import com.desirArman.tickets.domain.entities.EventStatusEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventRequestDto {

    @NotBlank(message = "Event Name is Required")
    private String name;

    @NotBlank(message = "Event venue information is Required")
    private String venue;

    private LocalDateTime start;
    private LocalDateTime end;

    private LocalDateTime salesStart;
    private LocalDateTime salesEnd;

    @NotNull(message ="Event Status must be provided")
    @Valid
    private EventStatusEnum status;

    @NotNull(message = "At least one ticket type is required")
    @Valid
    private List<CreateTicketTypeRequestDto> ticketTypes;
}