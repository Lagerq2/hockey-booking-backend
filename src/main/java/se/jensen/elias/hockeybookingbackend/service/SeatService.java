package se.jensen.elias.hockeybookingbackend.service;

import org.springframework.stereotype.Service;
import se.jensen.elias.hockeybookingbackend.dto.SeatResponseDTO;
import se.jensen.elias.hockeybookingbackend.mapper.SeatMapper;
import se.jensen.elias.hockeybookingbackend.model.Booking;
import se.jensen.elias.hockeybookingbackend.model.Match;
import se.jensen.elias.hockeybookingbackend.model.Seat;
import se.jensen.elias.hockeybookingbackend.repository.BookingRepository;
import se.jensen.elias.hockeybookingbackend.repository.SeatRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SeatService {

    private SeatRepository seatRepository;
    private BookingRepository bookingRepository;
    private SeatMapper seatMapper;

    public SeatService(SeatRepository seatRepository, BookingRepository bookingRepository, SeatMapper seatMapper) {
        this.seatRepository = seatRepository;
        this.bookingRepository = bookingRepository;
        this.seatMapper = seatMapper;
    }

    /*
    hämtar alla bookings för den specifika matchen,
    markera vilka stolar som är bokade,
    returnerar en dto med stolar + status
     */
    public List<SeatResponseDTO> getSeatsForMatch(Long matchId) {

        List<Seat> allSeats = seatRepository.findByMatchId(matchId);
        List<Booking> bookings = bookingRepository.findBySeat_Match_Id(matchId);

        //skapar en set-lista med bokade seat-id
        Set<Long> bookedSeatsIds = bookings.stream()
                .map(b -> b.getSeat().getSeatId())
                .collect(Collectors.toSet());

        return allSeats.stream()
                .map(seat -> seatMapper.toDTO(seat, bookedSeatsIds))
                .toList();

    }

    public void generateSeatsForMatch(Match match) {
        for (int seatNumber = 1; seatNumber <= 50; seatNumber++) {
            Seat seat = new Seat();
            seat.setMatchSeats(match);
            seat.setSeatNumber(seatNumber);
            seatRepository.save(seat);
        }

    }
}
