package se.jensen.elias.hockeybookingbackend.mapper;

import org.springframework.stereotype.Component;
import se.jensen.elias.hockeybookingbackend.dto.MatchRequestDTO;
import se.jensen.elias.hockeybookingbackend.dto.MatchResponseDTO;
import se.jensen.elias.hockeybookingbackend.model.Match;

@Component
public class MatchMapper {

    public MatchResponseDTO mapToDTO(Match match) {
        return new MatchResponseDTO(
                match.getId(),
                match.getMatch(),
                match.getMatchDateTime()
        );
    }

    public Match mapToEntity(MatchRequestDTO matchRequestDTO) {
        return new Match(
                matchRequestDTO.match(),
                matchRequestDTO.matchDateTime()
        );
    }
}
