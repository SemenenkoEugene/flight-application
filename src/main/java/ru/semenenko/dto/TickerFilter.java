package ru.semenenko.dto;

public record TickerFilter(String passengerName,
                           String seatNo,
                           int limit,
                           int offset) {
}
