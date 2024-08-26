package com.sventheeagle.buffalo_bar_server.dto;

public record AddPlayerToPackDTO(
        String playerId,
        String packId,
        boolean isAdmin
) {
}
