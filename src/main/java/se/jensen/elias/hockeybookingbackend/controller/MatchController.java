package se.jensen.elias.hockeybookingbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.jensen.elias.hockeybookingbackend.dto.MatchResponseDTO;
import se.jensen.elias.hockeybookingbackend.model.Match;
import se.jensen.elias.hockeybookingbackend.service.MatchService;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {
    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public ResponseEntity<List<MatchResponseDTO>> getMatches() {
        return ResponseEntity.ok(matchService.getMatches());
    }

    @GetMapping("/{matchId}")
    public ResponseEntity<Match> getMatchById(@PathVariable Long matchId) {
        return ResponseEntity.ok(matchService.getMatchById(matchId));
    }
}