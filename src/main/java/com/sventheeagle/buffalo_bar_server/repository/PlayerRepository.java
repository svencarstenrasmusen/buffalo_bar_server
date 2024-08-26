package com.sventheeagle.buffalo_bar_server.repository;

import com.sventheeagle.buffalo_bar_server.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, String> {
    Player findByUsername(String username);
}
