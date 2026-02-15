package se.jensen.elias.hockeybookingbackend.dto;

public record BookingRequestDTO(
        Long matchId,
        Long seatId,
        String phoneNumber
) {
}
