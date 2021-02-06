package com.escola.bootcamp.repositories;

import com.escola.bootcamp.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    Grade findByNome(String nome);
}
