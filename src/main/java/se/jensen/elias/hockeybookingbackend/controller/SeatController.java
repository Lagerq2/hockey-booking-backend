package se.jensen.elias.hockeybookingbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import se.jensen.elias.hockeybookingbackend.dto.SeatResponseDTO;
import se.jensen.elias.hockeybookingbackend.service.SeatService;

import java.util.List;

@RestController
public class SeatController {
    private SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/matches/{matchId}/seats")
    public ResponseEntity<List<SeatResponseDTO>> getSeatsForMatch(@PathVariable Long matchId) {
        return ResponseEntity.ok(seatService.getSeatsForMatch(matchId));
    }
}
