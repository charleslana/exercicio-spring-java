package com.escola.bootcamp.repositories;

import com.escola.bootcamp.entities.Alunos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunosRepository extends JpaRepository<Alunos, Long> {
    Alunos findByNome(String nome);
}
