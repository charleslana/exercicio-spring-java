package com.escola.bootcamp.services;

import com.escola.bootcamp.entities.Grade;
import com.escola.bootcamp.repositories.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;

    public Grade criar(Grade grade) {
        if (gradeRepository.findByNome(grade.getNome()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe uma grade com este nome.");
        }
        return gradeRepository.save(grade);
    }

    public List<Grade> listar() {
        return gradeRepository.findAll();
    }
}
