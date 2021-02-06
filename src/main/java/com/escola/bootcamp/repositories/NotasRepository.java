package com.escola.bootcamp.repositories;

import com.escola.bootcamp.entities.Notas;
import com.escola.bootcamp.enumerations.NotasStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotasRepository extends JpaRepository<Notas, Long> {

    @Query("SELECT (COUNT(n) > 0) FROM Notas n WHERE n.aluno.id = :alunoId and n.status = :status and n.materia.id <> :materiaId")
    boolean findAlunoIdAndStatusAndDifferentMateriaId(@Param("alunoId") Long alunoId, @Param("status") NotasStatusEnum status,
                               @Param("materiaId") Long materiaId);

    @Query("SELECT n FROM Notas n WHERE n.aluno.id = :alunoId and n.status = :status and n.materia.id = :materiaId")
    List<Notas> findAlunoIdAndStatusAndMateriaId(@Param("alunoId") Long alunoId, @Param("status") NotasStatusEnum status,
                                          @Param("materiaId") Long materiaId);

    List<Notas> findAllByAlunoId(Long id);

    @Query("SELECT COALESCE(SUM(n.valor), 0) FROM Notas n WHERE n.aluno.id = :alunoId")
    Integer sumValorAlunos(Long alunoId);
}