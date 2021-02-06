package com.escola.bootcamp.entities;

import com.escola.bootcamp.enumerations.NotasStatusEnum;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "notas")
public class Notas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private Alunos aluno;

    @ManyToOne
    @NotNull
    private MateriasEscolares materia;

    @NotNull
    private Integer valor;

    @Enumerated(EnumType.STRING)
    private NotasStatusEnum status = NotasStatusEnum.NAO_CONCLUIDO;
}
