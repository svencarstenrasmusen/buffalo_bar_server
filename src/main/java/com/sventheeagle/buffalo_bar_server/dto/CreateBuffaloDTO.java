package com.sventheeagle.buffalo_bar_server.dto;

public record CreateBuffaloDTO(
        String scalperId,
        String snaggeeId,
        double latitude,
        double longitude
) {
}
