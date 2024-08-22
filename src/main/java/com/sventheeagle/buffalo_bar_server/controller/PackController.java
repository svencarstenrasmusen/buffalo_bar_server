package com.sventheeagle.buffalo_bar_server.controller;

import com.sventheeagle.buffalo_bar_server.dto.PackCreatDTO;
import com.sventheeagle.buffalo_bar_server.model.Pack;
import com.sventheeagle.buffalo_bar_server.service.PackService;
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

    public PackController(PackService packService) {
        this.packService = packService;
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
    public ResponseEntity<Pack> createGroup(@RequestBody PackCreatDTO dto) {
        return new ResponseEntity<>(packService.createPack(dto), HttpStatus.OK);
    }
}
