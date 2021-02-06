package com.escola.bootcamp.controllers;

import com.escola.bootcamp.entities.Alunos;
import com.escola.bootcamp.objetos.HistoricoAlunos;
import com.escola.bootcamp.objetos.MelhoresAlunos;
import com.escola.bootcamp.services.AlunosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
public class AlunosController {

    private final AlunosService alunosService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Alunos criar(@RequestBody Alunos alunos) {
        return alunosService.criar(alunos);
    }

    @GetMapping
    public List<Alunos> listar() {
        return alunosService.listar();
    }

    @GetMapping("/{id}")
    public Alunos listarPorId(@PathVariable Long id) {
        return alunosService.listarPorId(id);
    }

    @GetMapping("/historico")
    public List<HistoricoAlunos> historico() {
        return alunosService.historico();
    }

    @GetMapping("/historico/{id}")
    public HistoricoAlunos historico(@PathVariable Long id) {
        return alunosService.historicoPorId(id);
    }

    @GetMapping("/melhores")
    public List<MelhoresAlunos> melhores() {
        return alunosService.melhoresAlunos();
    }
}
