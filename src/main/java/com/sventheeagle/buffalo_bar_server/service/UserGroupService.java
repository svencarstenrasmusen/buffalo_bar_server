package com.sventheeagle.buffalo_bar_server.service;

import com.sventheeagle.buffalo_bar_server.dto.AddUserToGroupDTO;
import com.sventheeagle.buffalo_bar_server.dto.RemoveUserFromGroupDTO;
import com.sventheeagle.buffalo_bar_server.model.UserGroup;
import com.sventheeagle.buffalo_bar_server.repository.UserGroupRepository;
import org.springframework.stereotype.Service;

@Service
public class UserGroupService {
    private final UserGroupRepository userGroupRepository;
    private final UserService userService;
    private final GroupService groupService;

    public UserGroupService(UserGroupRepository userGroupRepository, UserService userService, GroupService groupService) {
        this.userGroupRepository = userGroupRepository;
        this.userService = userService;
        this.groupService = groupService;
    }

    public UserGroup addUserToGroup(AddUserToGroupDTO dto) {
        UserGroup userGroup = new UserGroup();
        userGroup.setUser(userService.getUserById(dto.userId()));
        userGroup.setGroup(groupService.getGroupById(dto.groupId()));
        userGroup.setAdmin(dto.isAdmin());

        return userGroupRepository.save(userGroup);
    }

    public void removeUserFromGroup(RemoveUserFromGroupDTO dto) {
        UserGroup userGroup = userGroupRepository.findByUserIdAndGroupId(dto.userId(), dto.groupId());
        userGroupRepository.delete(userGroup);
    }
}
