package com.sventheeagle.buffalo_bar_server.service;

import com.sventheeagle.buffalo_bar_server.dto.CreateBuffaloDTO;
import com.sventheeagle.buffalo_bar_server.model.Buffalo;
import com.sventheeagle.buffalo_bar_server.repository.BuffaloRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BuffaloService {
    private final BuffaloRepository buffaloRepository;
    private final PlayerService playerService;

    public BuffaloService(BuffaloRepository buffaloRepository, PlayerService playerService) {
        this.buffaloRepository = buffaloRepository;
        this.playerService = playerService;
    }

    public Buffalo createBuffalo(CreateBuffaloDTO dto) {
        //Find Shared Groups
        //List<Group> sharedGroups = userGroupRepository.findSharedGroups(dto.scalperId(), dto.snaggeeId());

        /**if (sharedGroups.isEmpty()) {
            throw new RuntimeException("Users do not have any groups in common.");
        }*/

        //Create & Save Buffalo
        Buffalo buffalo = new Buffalo();
        buffalo.setScalper(playerService.getPlayerById(dto.scalperId()));
        buffalo.setSnaggee(playerService.getPlayerById(dto.snaggeeId()));
        buffalo.setLongitude(dto.longitude());
        buffalo.setLatitude(dto.latitude());

        /**Buffalo savedBuffalo = buffaloRepository.save(buffalo);

        for (Group group : sharedGroups) {
            BuffaloGroup buffaloGroup = new BuffaloGroup();
            buffaloGroup.setBuffalo(savedBuffalo);
            buffaloGroup.setGroup(group);
            buffaloGroupRepository.save(buffaloGroup);
        }*/

        return buffalo;
    }

    public Buffalo getBuffaloById(@NonNull String id) {
        return buffaloRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Buffalo does not exist."));
    }

    public List<Buffalo> getAllBuffaloes() {
        return buffaloRepository.findAll();
    }

    public List<Buffalo> getAllBuffaloesByScalperId(@NonNull String id) {
        return buffaloRepository.findAllByScalperId(id);
    }

    public List<Buffalo> getAllBuffaloesBySnaggeeId(@NonNull String id) {
        return buffaloRepository.findAllBySnaggeeId(id);
    }

    public void deleteBuffaloById(@NonNull String id) {
        buffaloRepository.deleteById(id);
    }
}
