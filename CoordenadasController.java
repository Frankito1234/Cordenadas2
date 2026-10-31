package com.incidencias.controller;

import com.incidencias.dto.CoordenadasDTO;
import com.incidencias.service.CoordenadasService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coordenadas")
public class CoordenadasController {

    private final CoordenadasService service;

    public CoordenadasController(CoordenadasService service) {
        this.service = service;
    }

    @GetMapping
    public List<CoordenadasDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public CoordenadasDTO obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<CoordenadasDTO> crear(@Valid @RequestBody CoordenadasDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @PutMapping("/{id}")
    public CoordenadasDTO actualizar(@PathVariable Long id, @Valid @RequestBody CoordenadasDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
