package ru.semenenko.dto;

public record TicketDto(Long id,
                        Long flightId,
                        String seatNo) {
}
