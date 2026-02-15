package se.jensen.elias.hockeybookingbackend.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime timeStamp,
        int status,
        String message
) {
}
