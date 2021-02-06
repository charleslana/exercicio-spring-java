package com.escola.bootcamp.services;

import com.escola.bootcamp.entities.MateriasEscolares;
import com.escola.bootcamp.repositories.MateriasEscolaresRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MateriasEscolaresService {

    private final MateriasEscolaresRepository materiasEscolaresRepository;

    public MateriasEscolares criar(MateriasEscolares materiasEscolares) {
        if (materiasEscolaresRepository.findByNome(materiasEscolares.getNome()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe uma matéria com este nome.");
        }
        return materiasEscolaresRepository.save(materiasEscolares);
    }

    public List<MateriasEscolares> listar() {
        return materiasEscolaresRepository.findAll();
    }
}
