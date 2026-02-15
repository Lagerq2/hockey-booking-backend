package se.jensen.elias.hockeybookingbackend.mapper;

import org.springframework.stereotype.Component;
import se.jensen.elias.hockeybookingbackend.dto.BookingResponseDTO;
import se.jensen.elias.hockeybookingbackend.model.Booking;

import java.time.LocalDateTime;

@Component
public class BookingMapper {

    public BookingResponseDTO bookingToBookingResponseDTO(Booking booking) {
        String matchName = booking.getSeat().getMatch().getMatch();
        int seatNumber = booking.getSeat().getSeatNumber();
        LocalDateTime matchDateTime = booking.getSeat().getMatch().getMatchDateTime();

        return new BookingResponseDTO(
                matchName,
                seatNumber,
                matchDateTime
        );
    }
}
