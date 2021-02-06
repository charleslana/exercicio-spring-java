package com.escola.bootcamp.controllers;

import com.escola.bootcamp.entities.MateriasEscolares;
import com.escola.bootcamp.services.MateriasEscolaresService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materias")
@RequiredArgsConstructor
public class MateriasEscolaresController {

    private final MateriasEscolaresService materiasEscolaresService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MateriasEscolares criar(@RequestBody MateriasEscolares materiasEscolares) {
        return materiasEscolaresService.criar(materiasEscolares);
    }

    @GetMapping
    public List<MateriasEscolares> listar() {
        return materiasEscolaresService.listar();
    }
}