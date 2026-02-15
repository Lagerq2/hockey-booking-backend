package se.jensen.elias.hockeybookingbackend.dto;

import java.time.LocalDateTime;

public record BookingResponseDTO(
        String match,
        int seatNumber,
        LocalDateTime matchDateTime) {
}
