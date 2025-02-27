package com.sventheeagle.buffalo_bar_server.service;

import com.sventheeagle.buffalo_bar_server.dto.AddPlayerToPackDTO;
import com.sventheeagle.buffalo_bar_server.dto.PackCreateDTO;
import com.sventheeagle.buffalo_bar_server.model.Pack;
import com.sventheeagle.buffalo_bar_server.model.Player;
import com.sventheeagle.buffalo_bar_server.repository.PackRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PackService {

    private final PackRepository packRepository;

    public PackService(PackRepository packRepository) {
        this.packRepository = packRepository;
    }

    public Pack createPack(PackCreateDTO dto) {
        Pack pack = new Pack();
        pack.setName(dto.name());

        return packRepository.save(pack);
    }

    public Pack getPackById(@NonNull String id) {
        return packRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Group with ID: " + id + "does not exist."));
    }

    public List<Pack> getAllPacks() {
        return packRepository.findAll();
    }
}
