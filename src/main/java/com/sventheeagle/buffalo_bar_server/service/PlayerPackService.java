package com.sventheeagle.buffalo_bar_server.service;

import com.sventheeagle.buffalo_bar_server.dto.AddPlayerToPackDTO;
import com.sventheeagle.buffalo_bar_server.model.Pack;
import com.sventheeagle.buffalo_bar_server.model.Player;
import com.sventheeagle.buffalo_bar_server.model.PlayerPack;
import com.sventheeagle.buffalo_bar_server.repository.PlayerPackRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PlayerPackService {
    private final PlayerPackRepository playerPackRepository;
    private final PlayerService playerService;
    private final PackService packService;

    public PlayerPackService(PlayerPackRepository playerPackRepository, PlayerService playerService, PackService packService) {
        this.playerPackRepository = playerPackRepository;
        this.playerService = playerService;
        this.packService = packService;
    }

    public PlayerPack addPlayerToPack(AddPlayerToPackDTO dto) {
        Player player = playerService.getPlayerById(dto.playerId());
        Pack pack = packService.getPackById(dto.packId());

        PlayerPack playerPack = new PlayerPack();
        playerPack.setPlayer(player);
        playerPack.setPack(pack);
        playerPack.setJoinedAt(LocalDateTime.now());
        playerPack.setAdmin(dto.isAdmin());

        return playerPackRepository.save(playerPack);
    }

    public List<PlayerPack> findPackByPlayerId(String id) {
        return playerPackRepository.findByPlayerId(id);
    }

    public List<PlayerPack> getPlayersByPackId(String id) {
        return playerPackRepository.findAllByPackId(id);
    }
}
