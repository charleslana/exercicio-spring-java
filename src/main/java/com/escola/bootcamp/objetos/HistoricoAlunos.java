package com.escola.bootcamp.objetos;

import com.escola.bootcamp.entities.Alunos;
import com.escola.bootcamp.entities.Notas;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HistoricoAlunos {

    private Alunos aluno;

    private List<Notas> notas;
}
