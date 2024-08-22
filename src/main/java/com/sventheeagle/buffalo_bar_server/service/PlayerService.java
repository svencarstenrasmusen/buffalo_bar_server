package com.sventheeagle.buffalo_bar_server.service;

import com.sventheeagle.buffalo_bar_server.model.Player;
import com.sventheeagle.buffalo_bar_server.repository.PlayerRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player getPlayerById(@NonNull String id) {
        return playerRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Player does not exist."));
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }
}
