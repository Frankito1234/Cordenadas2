package com.incidencias.service;

import com.incidencias.dto.CoordenadasDTO;
import com.incidencias.model.Coordenadas;
import com.incidencias.repository.CoordenadasRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CoordenadasService {

    private final CoordenadasRepository repository;

    public CoordenadasService(CoordenadasRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<CoordenadasDTO> listar() {
        return repository.findAll().stream().map(this::aDTO).toList();
    }

    @Transactional(readOnly = true)
    public CoordenadasDTO obtenerPorId(Long id) {
        return aDTO(buscar(id));
    }

    @Transactional
    public CoordenadasDTO crear(CoordenadasDTO dto) {
        Coordenadas entidad = new Coordenadas(dto.getLatitud(), dto.getLongitud());
        return aDTO(repository.save(entidad));
    }

    @Transactional
    public CoordenadasDTO actualizar(Long id, CoordenadasDTO dto) {
        Coordenadas entidad = buscar(id);
        entidad.setLatitud(dto.getLatitud());
        entidad.setLongitud(dto.getLongitud());
        return aDTO(repository.save(entidad));
    }

    @Transactional
    public void eliminar(Long id) {
        repository.delete(buscar(id));
    }

    private Coordenadas buscar(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No existen coordenadas con id " + id));
    }

    private CoordenadasDTO aDTO(Coordenadas c) {
        return new CoordenadasDTO(c.getId(), c.getLatitud(), c.getLongitud());
    }
}
