package com.escola.bootcamp.repositories;

import com.escola.bootcamp.entities.MateriasEscolares;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriasEscolaresRepository extends JpaRepository<MateriasEscolares, Long> {
    MateriasEscolares findByNome(String nome);
}
