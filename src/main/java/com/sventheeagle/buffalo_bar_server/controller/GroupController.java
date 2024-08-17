package com.sventheeagle.buffalo_bar_server.controller;

import com.sventheeagle.buffalo_bar_server.dto.GroupCreateDTO;
import com.sventheeagle.buffalo_bar_server.model.Group;
import com.sventheeagle.buffalo_bar_server.service.GroupService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/group")
@Tag(name = "Group")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Group> getGroupById(@PathVariable("id") String id) {
        return new ResponseEntity<>(groupService.getGroupById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Group>> getAllGroups() {
        return new ResponseEntity<>(groupService.getAllGroups(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestBody GroupCreateDTO dto) {
        return new ResponseEntity<>(groupService.createGroup(dto), HttpStatus.OK);
    }
}
