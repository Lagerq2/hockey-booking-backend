package se.jensen.elias.hockeybookingbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.jensen.elias.hockeybookingbackend.dto.BookingResponseDTO;
import se.jensen.elias.hockeybookingbackend.dto.MatchRequestDTO;
import se.jensen.elias.hockeybookingbackend.dto.MatchResponseDTO;
import se.jensen.elias.hockeybookingbackend.service.BookingService;
import se.jensen.elias.hockeybookingbackend.service.MatchService;
import se.jensen.elias.hockeybookingbackend.service.SeatService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    MatchService matchService;
    BookingService bookingService;
    SeatService seatService;

    public AdminController(MatchService matchService, BookingService bookingService, SeatService seatService) {
        this.matchService = matchService;
        this.bookingService = bookingService;
        this.seatService = seatService;
    }

    @PostMapping("/matches")
    public ResponseEntity<MatchResponseDTO> createMatches(@RequestBody MatchRequestDTO matchRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(matchService.createMatch(matchRequestDTO));
    }

    @GetMapping("/matches/{matchId}/bookings")
    public ResponseEntity<List<BookingResponseDTO>> getBookingsForMatch(
            @PathVariable Long matchId) {
        return ResponseEntity.ok(
                bookingService.getAllBookingsForMatch(matchId));

    }

    @DeleteMapping("/matches/{matchId}")
    public void deleteMatch(@PathVariable Long matchId) {
        matchService.deleteMatch(matchId);
    }

    @PutMapping("/matches/{matchId}")
    public ResponseEntity<MatchResponseDTO> updateMatch(@RequestBody MatchRequestDTO update, @PathVariable Long matchId) {
        return ResponseEntity.ok(matchService.updateMatch(update, matchId));
    }
}
