package se.jensen.elias.hockeybookingbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.jensen.elias.hockeybookingbackend.dto.BookingRequestDTO;
import se.jensen.elias.hockeybookingbackend.dto.BookingResponseDTO;
import se.jensen.elias.hockeybookingbackend.service.BookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingResponseDTO> BookSeat(@RequestBody BookingRequestDTO bookingRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookingService.bookSeat(bookingRequestDTO));
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable Long bookingId) {
        return ResponseEntity.ok(bookingService.getBookingById(bookingId));
    }

    @DeleteMapping("/{bookingId}")
    public void deleteBooking(@PathVariable Long bookingId) {
        bookingService.deleteBooking(bookingId);
    }
}
