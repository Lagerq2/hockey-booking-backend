package se.jensen.elias.hockeybookingbackend.dto;

import java.time.LocalDateTime;

public record MatchRequestDTO(String match, LocalDateTime matchDateTime) {
}
