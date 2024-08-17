package com.sventheeagle.buffalo_bar_server.dto;

public record RemoveUserFromGroupDTO(
        String userId,
        String groupId
) {
}
