package com.escola.bootcamp.controllers;

import com.escola.bootcamp.entities.Notas;
import com.escola.bootcamp.services.NotasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notas")
@RequiredArgsConstructor
public class NotasController {

    private final NotasService notasService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Notas lancar(@RequestBody Notas notas) {
        return notasService.lancar(notas);
    }

    @GetMapping
    public List<Notas> listar() {
        return notasService.listar();
    }
}
