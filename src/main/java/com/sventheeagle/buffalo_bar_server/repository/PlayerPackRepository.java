package com.sventheeagle.buffalo_bar_server.repository;

import com.sventheeagle.buffalo_bar_server.model.Player;
import com.sventheeagle.buffalo_bar_server.model.PlayerPack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerPackRepository extends JpaRepository<PlayerPack, String> {
    List<PlayerPack> findByPlayerId(String id);
    List<PlayerPack> findAllByPackId(String id);
}
