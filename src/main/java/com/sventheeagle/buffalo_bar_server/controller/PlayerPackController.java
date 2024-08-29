package com.sventheeagle.buffalo_bar_server.controller;

import com.sventheeagle.buffalo_bar_server.dto.AddPlayerToPackDTO;
import com.sventheeagle.buffalo_bar_server.model.PlayerPack;
import com.sventheeagle.buffalo_bar_server.service.PlayerPackService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/playerPack")
@Tag(name = "PlayerPack")
public class PlayerPackController {
    private final PlayerPackService playerPackService;

    public PlayerPackController(PlayerPackService playerPackService) {
        this.playerPackService = playerPackService;
    }

    @PostMapping
    public ResponseEntity<PlayerPack> addPlayerToPack(@RequestBody AddPlayerToPackDTO dto) {
        return new ResponseEntity<>(playerPackService.addPlayerToPack(dto), HttpStatus.OK);
    }

    @GetMapping("/players/{id}")
    public ResponseEntity<List<PlayerPack>> getAllPlayerFromPack(@PathVariable("id") String id) {
        return new ResponseEntity<>(playerPackService.getPlayersByPackId(id), HttpStatus.OK);
    }

    @GetMapping("/packs/{id}")
    public ResponseEntity<List<PlayerPack>> getAllPacksFromPlayer(@PathVariable("id") String id) {
        return new ResponseEntity<>(playerPackService.findPackByPlayerId(id), HttpStatus.OK);
    }
}
