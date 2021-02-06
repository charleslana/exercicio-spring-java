package com.escola.bootcamp.controllers;

import com.escola.bootcamp.entities.Grade;
import com.escola.bootcamp.services.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grade")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Grade criar(@RequestBody Grade grade) {
        return gradeService.criar(grade);
    }

    @GetMapping
    public List<Grade> listar() {
        return gradeService.listar();
    }
}
