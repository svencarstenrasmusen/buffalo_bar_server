package com.sventheeagle.buffalo_bar_server.controller;

import com.sventheeagle.buffalo_bar_server.dto.AddPlayerToPackDTO;
import com.sventheeagle.buffalo_bar_server.dto.PackCreateDTO;
import com.sventheeagle.buffalo_bar_server.model.Pack;
import com.sventheeagle.buffalo_bar_server.service.PackService;
import com.sventheeagle.buffalo_bar_server.service.PlayerPackService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/pack")
@Tag(name = "Pack")
public class PackController {

    private final PackService packService;
    private final PlayerPackService playerPackService;

    public PackController(PackService packService, PlayerPackService playerPackService) {
        this.packService = packService;
        this.playerPackService = playerPackService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Pack> getGroupById(@PathVariable("id") String id) {
        return new ResponseEntity<>(packService.getPackById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pack>> getAllGroups() {
        return new ResponseEntity<>(packService.getAllPacks(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pack> createGroup(@RequestBody PackCreateDTO dto) {
        Pack createdPack = packService.createPack(dto);
        AddPlayerToPackDTO addDto = new AddPlayerToPackDTO(
                dto.playerId(),
                createdPack.getId(),
                true
        );

        playerPackService.addPlayerToPack(addDto);
        return new ResponseEntity<>(createdPack, HttpStatus.OK);
    }
}
