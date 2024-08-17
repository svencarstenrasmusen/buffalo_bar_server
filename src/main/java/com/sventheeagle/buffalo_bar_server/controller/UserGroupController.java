package com.sventheeagle.buffalo_bar_server.controller;

import com.sventheeagle.buffalo_bar_server.dto.AddUserToGroupDTO;
import com.sventheeagle.buffalo_bar_server.dto.RemoveUserFromGroupDTO;
import com.sventheeagle.buffalo_bar_server.model.UserGroup;
import com.sventheeagle.buffalo_bar_server.service.UserGroupService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/usergroup")
@Tag(name = "UserGroup")
public class UserGroupController {
    private final UserGroupService userGroupService;

    public UserGroupController(UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }

    @PostMapping("/add")
    public ResponseEntity<UserGroup> addUserToGroup(@RequestBody AddUserToGroupDTO dto) {
        return new ResponseEntity<>(userGroupService.addUserToGroup(dto), HttpStatus.OK);
    }

    @PostMapping("/remove")
    public ResponseEntity<Void> removeUserFromGroup(@RequestBody RemoveUserFromGroupDTO dto) {
        userGroupService.removeUserFromGroup(dto);
        return ResponseEntity.ok(null);
    }
}
