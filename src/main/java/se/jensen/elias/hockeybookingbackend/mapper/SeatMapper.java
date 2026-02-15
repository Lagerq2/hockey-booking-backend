package se.jensen.elias.hockeybookingbackend.mapper;

import org.springframework.stereotype.Component;
import se.jensen.elias.hockeybookingbackend.dto.SeatResponseDTO;
import se.jensen.elias.hockeybookingbackend.model.Booking;
import se.jensen.elias.hockeybookingbackend.model.Seat;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SeatMapper {

    public SeatResponseDTO toDTO(Seat seat, Set<Long> bookedSeatsIds) {

        boolean isBooked = bookedSeatsIds.contains(seat.getSeatId());

        return new  SeatResponseDTO(
                seat.getSeatId(),
                seat.getSeatNumber(),
                isBooked

        );
    }
}
