package se.jensen.elias.hockeybookingbackend.service;

import org.springframework.stereotype.Service;
import se.jensen.elias.hockeybookingbackend.dto.BookingRequestDTO;
import se.jensen.elias.hockeybookingbackend.dto.BookingResponseDTO;
import se.jensen.elias.hockeybookingbackend.exception.ResourceNotFoundException;
import se.jensen.elias.hockeybookingbackend.exception.SeatAlreadyBookedException;
import se.jensen.elias.hockeybookingbackend.mapper.BookingMapper;
import se.jensen.elias.hockeybookingbackend.model.Booking;
import se.jensen.elias.hockeybookingbackend.model.Match;
import se.jensen.elias.hockeybookingbackend.model.Seat;
import se.jensen.elias.hockeybookingbackend.repository.BookingRepository;
import se.jensen.elias.hockeybookingbackend.repository.MatchRepository;
import se.jensen.elias.hockeybookingbackend.repository.SeatRepository;

import java.security.SecureRandom;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final MatchRepository matchRepository;
    private final SeatRepository seatRepository;
    private final BookingMapper bookingMapper;

    public BookingService(BookingRepository bookingRepository, MatchRepository matchRepository, SeatRepository seatRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.matchRepository = matchRepository;
        this.seatRepository = seatRepository;
        this.bookingMapper = bookingMapper;
    }

    //returnerar alla bokningar på vald match(till admin)
    public List<BookingResponseDTO> getAllBookingsForMatch(Long matchId) {
        if (!matchRepository.existsById(matchId)) {
            throw new ResourceNotFoundException("Match with id " + matchId + " not found");
        }
        return bookingRepository.findBySeat_Match_Id(matchId)
                .stream()
                .map(bookingMapper::bookingToBookingResponseDTO)
                .toList();
    }

    /*
    hämta matchen och seat id,
    kontrollera att stolen är ledig,
    skapa en bokning
     */
    public BookingResponseDTO bookSeat(BookingRequestDTO bookingRequest) {

        Match match = matchRepository.findById(bookingRequest.matchId())
                .orElseThrow(() -> new ResourceNotFoundException("Match with id " + bookingRequest.matchId() + " not found"));

        Seat seat = seatRepository.findById(bookingRequest.seatId())
                .orElseThrow(() -> new ResourceNotFoundException("Seat with id " + bookingRequest.seatId() + " not found"));

        boolean alreadyBooked = bookingRepository.existsBySeat_Match_IdAndSeat_Id(match.getId(), seat.getSeatId());
        if (alreadyBooked) {
            throw new SeatAlreadyBookedException("Seat already booked for this match");
        }

        Booking booking = new Booking(
                match,
                seat,
                bookingRequest.phoneNumber(),
                false,
                generateTicketCode(seat)
        );
        Booking savedBooking = bookingRepository.save(booking);
        return bookingMapper.bookingToBookingResponseDTO(booking);
    }

    public BookingResponseDTO getBookingById(Long bookingId) {
        return bookingMapper.bookingToBookingResponseDTO(bookingRepository.findById(bookingId)
                .orElseThrow(()
                        -> new ResourceNotFoundException
                        ("Booking with id " + bookingId + " not found"))
        );

    }

    public void deleteBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    //genererar kod som ska skicka till kund vid bokning för verifiering i hallen
    private String generateTicketCode(Seat seat) {

        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom random = new SecureRandom();
        String code = "";
        for (int i = 0; i < 4; i++) {
            code += chars.charAt(random.nextInt(chars.length()));
        }
        return seat.getSeatId() + "-" + code;
    }
}
