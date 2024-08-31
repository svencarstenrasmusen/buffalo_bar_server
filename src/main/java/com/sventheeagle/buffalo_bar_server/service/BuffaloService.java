package com.sventheeagle.buffalo_bar_server.service;

import com.sventheeagle.buffalo_bar_server.dto.CreateBuffaloDTO;
import com.sventheeagle.buffalo_bar_server.model.Buffalo;
import com.sventheeagle.buffalo_bar_server.model.BuffaloPack;
import com.sventheeagle.buffalo_bar_server.model.Pack;
import com.sventheeagle.buffalo_bar_server.model.PlayerPack;
import com.sventheeagle.buffalo_bar_server.repository.BuffaloPackRepository;
import com.sventheeagle.buffalo_bar_server.repository.BuffaloRepository;
import com.sventheeagle.buffalo_bar_server.repository.PlayerPackRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class BuffaloService {
    private final BuffaloRepository buffaloRepository;
    private final BuffaloPackRepository buffaloPackRepository;
    private final PlayerPackService playerPackService;
    private final PlayerService playerService;

    public BuffaloService(BuffaloRepository buffaloRepository, BuffaloPackRepository buffaloPackRepository, PlayerPackRepository playerPackRepository, BuffaloPackRepository buffaloPackRepository1, PlayerPackService playerPackService, PlayerService playerService) {
        this.buffaloRepository = buffaloRepository;
        this.buffaloPackRepository = buffaloPackRepository1;
        this.playerPackService = playerPackService;
        this.playerService = playerService;
    }

    public Buffalo createBuffalo(CreateBuffaloDTO dto) {
        //Get scalper's groups
        List<PlayerPack> scalperPlayerPacks = playerPackService.findPackByPlayerId(dto.scalperId());
        List<Pack> scalperPacks = new ArrayList<>();

        for (PlayerPack scalperPlayerPack : scalperPlayerPacks) {
            scalperPacks.add(scalperPlayerPack.getPack());
        }

        //Get snaggee's groups
        List<PlayerPack> snaggeePlayerPacks = playerPackService.findPackByPlayerId(dto.snaggeeId());
        List<Pack> snaggeePacks = new ArrayList<>();

        for (PlayerPack snaggeePlayerPack : snaggeePlayerPacks) {
            snaggeePacks.add(snaggeePlayerPack.getPack());
        }

        //Find Shared Groups
        List<Pack> sharedPacks = scalperPacks.stream().filter(snaggeePacks::contains).toList();

        if (sharedPacks.isEmpty()) {
            throw new NoSuchElementException("Users do not have any groups in common.");
        }

        //Create & Save Buffalo
        Buffalo buffalo = new Buffalo();
        buffalo.setScalper(playerService.getPlayerById(dto.scalperId()));
        buffalo.setSnaggee(playerService.getPlayerById(dto.snaggeeId()));
        buffalo.setLongitude(dto.longitude());
        buffalo.setLatitude(dto.latitude());

        Buffalo savedBuffalo = buffaloRepository.save(buffalo);

        for (Pack pack : sharedPacks) {
            BuffaloPack buffaloPack = new BuffaloPack();
            buffaloPack.setBuffalo(savedBuffalo);
            buffaloPack.setPack(pack);
            buffaloPackRepository.save(buffaloPack);
        }

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
