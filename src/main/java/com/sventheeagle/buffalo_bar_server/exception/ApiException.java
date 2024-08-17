package com.sventheeagle.buffalo_bar_server.exception;
import org.springframework.http.HttpStatus;
import java.time.ZonedDateTime;

public record ApiException(
        String message,
        HttpStatus httpStatus,
        ZonedDateTime timestamp
) {
}
