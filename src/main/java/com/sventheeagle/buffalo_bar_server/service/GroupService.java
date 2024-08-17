package com.sventheeagle.buffalo_bar_server.service;

import com.sventheeagle.buffalo_bar_server.dto.GroupCreateDTO;
import com.sventheeagle.buffalo_bar_server.model.Group;
import com.sventheeagle.buffalo_bar_server.repository.GroupRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group createGroup(GroupCreateDTO dto) {
        Group group = new Group();
        group.setName(dto.name());

        return groupRepository.save(group);
    }

    public Group getGroupById(@NonNull String id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Group with ID: " + id + "does not exist."));
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }
}
