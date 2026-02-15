package se.jensen.elias.hockeybookingbackend.dto;

import java.time.LocalDateTime;

public record MatchResponseDTO(
        Long id,
        String match,
        LocalDateTime matchDateTime
) {
}
