package com.sventheeagle.buffalo_bar_server.repository;

import com.sventheeagle.buffalo_bar_server.model.Pack;
import com.sventheeagle.buffalo_bar_server.model.Player;
import com.sventheeagle.buffalo_bar_server.model.PlayerPack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerPackRepository extends JpaRepository<PlayerPack, String> {
    List<PlayerPack> findByPlayerId(String id);
    List<PlayerPack> findAllByPackId(String id);

    /**@Query(value = "SELECT g.* FROM player_pack ug1 " +
            "JOIN player_pack ug2 ON ug1.pack_id = ug2.pack_id " +
            "JOIN pack g ON ug1.pack_id = g.id " +
            "WHERE ug1.player_id = :scalperId AND ug2.player_id = :snaggeeId",
            nativeQuery = true)
    List<Pack> findSharedPacks(@Param("scalperId") String scalperId, @Param("snaggeeId") String snaggeeId);*/
}
