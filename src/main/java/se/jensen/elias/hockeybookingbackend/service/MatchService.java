package se.jensen.elias.hockeybookingbackend.service;

import org.springframework.stereotype.Service;
import se.jensen.elias.hockeybookingbackend.dto.MatchRequestDTO;
import se.jensen.elias.hockeybookingbackend.dto.MatchResponseDTO;
import se.jensen.elias.hockeybookingbackend.exception.ResourceNotFoundException;
import se.jensen.elias.hockeybookingbackend.mapper.MatchMapper;
import se.jensen.elias.hockeybookingbackend.model.Match;
import se.jensen.elias.hockeybookingbackend.repository.MatchRepository;

import java.util.List;

@Service
public class MatchService {
    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;
    private final SeatService seatService;

    public MatchService(MatchRepository matchRepository, MatchMapper matchMapper, SeatService seatService) {
        this.matchRepository = matchRepository;
        this.matchMapper = matchMapper;
        this.seatService = seatService;
    }

    //skapar ny match(för admin)
    public MatchResponseDTO createMatch(MatchRequestDTO match) {
        Match newMatch = matchMapper.mapToEntity(match);
        Match savedMatch = matchRepository.save(newMatch);
        seatService.generateSeatsForMatch(savedMatch);

        return matchMapper.mapToDTO(savedMatch);
    }

    //uppdatera match(för admin)
    public MatchResponseDTO updateMatch(MatchRequestDTO update, Long matchId) {
        Match match = matchMapper.mapToEntity(update);
        Match updatedMatch = matchRepository.findById(matchId)
                .map(matchEntity -> {
                    matchEntity.setMatch(match.getMatch());
                    matchEntity.setMatchDateTime(match.getMatchDateTime());
                    return matchRepository.save(matchEntity);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Match with id " + matchId + " not found"));
        return matchMapper.mapToDTO(updatedMatch);
    }

    //radera match(för admin)
    public void deleteMatch(Long matchId) {
        matchRepository.deleteById(matchId);
    }

    public List<MatchResponseDTO> getMatches() {
        List<MatchResponseDTO> matches = matchRepository.findAll().stream()
                .map(matchMapper::mapToDTO)
                .toList();
        return matches;
    }

    public Match getMatchById(Long matchId) {
        return matchRepository.findById(matchId)
                .orElseThrow(() -> new ResourceNotFoundException("Match with id " + matchId + " not found"));
    }


}
