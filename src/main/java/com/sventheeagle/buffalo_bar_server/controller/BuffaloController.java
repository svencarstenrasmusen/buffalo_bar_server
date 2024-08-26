package com.sventheeagle.buffalo_bar_server.controller;

import com.sventheeagle.buffalo_bar_server.dto.CreateBuffaloDTO;
import com.sventheeagle.buffalo_bar_server.model.Buffalo;
import com.sventheeagle.buffalo_bar_server.service.BuffaloService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/buffalo")
@Tag(name = "Buffalo")
public class BuffaloController {
    private final BuffaloService buffaloService;

    public BuffaloController(BuffaloService buffaloService) {
        this.buffaloService = buffaloService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Buffalo>> getAllBuffaloes() {
        return new ResponseEntity<>(buffaloService.getAllBuffaloes(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Buffalo> getBuffaloById(@PathVariable("id") String id) {
        return new ResponseEntity<>(buffaloService.getBuffaloById(id), HttpStatus.OK);
    }

    @GetMapping("/scalper/{id}")
    public ResponseEntity<List<Buffalo>> getAllBuffaloesByScalperId(@PathVariable("id") String id) {
        return new ResponseEntity<>(buffaloService.getAllBuffaloesByScalperId(id), HttpStatus.OK);
    }

    @GetMapping("/snaggee/{id}")
    public ResponseEntity<List<Buffalo>> getAllBuffaloesBySnaggeeId(@PathVariable("id") String id) {
        return new ResponseEntity<>(buffaloService.getAllBuffaloesBySnaggeeId(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Buffalo> createBuffalo(@RequestBody CreateBuffaloDTO dto) {
        return new ResponseEntity<>(buffaloService.createBuffalo(dto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBuffaloById(@PathVariable("id") String id) {
        return ResponseEntity.ok(null);
    }
}
