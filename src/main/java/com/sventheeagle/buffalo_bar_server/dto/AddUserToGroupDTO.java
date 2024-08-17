package com.sventheeagle.buffalo_bar_server.dto;

public record AddUserToGroupDTO(
        String userId,
        String groupId,
        boolean isAdmin
) {
}
