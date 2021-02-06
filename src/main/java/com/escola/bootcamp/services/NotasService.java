package com.escola.bootcamp.services;

import com.escola.bootcamp.entities.Alunos;
import com.escola.bootcamp.entities.MateriasEscolares;
import com.escola.bootcamp.entities.Notas;
import com.escola.bootcamp.enumerations.NotasStatusEnum;
import com.escola.bootcamp.repositories.AlunosRepository;
import com.escola.bootcamp.repositories.MateriasEscolaresRepository;
import com.escola.bootcamp.repositories.NotasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotasService {

    private final NotasRepository notasRepository;
    private final AlunosRepository alunosRepository;
    private final MateriasEscolaresRepository materiasEscolaresRepository;

    public Notas lancar(Notas notas) {
        if (notas.getValor() < 0 || notas.getValor() > 100) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Informe um valor da nota entre 0 e 100");
        }

        Alunos aluno = alunosRepository.findById(notas.getAluno().getId()).orElse(null);

        if (aluno == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aluno não encontrado.");
        }

        MateriasEscolares materia = materiasEscolaresRepository.findById(notas.getMateria().getId()).orElse(null);

        if (materia == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Materia não encontrada.");
        }

        if (!aluno.getGrade().getMaterias().contains(materia)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Materia da grade de aluno não encontrada.");
        }

        if (!notasRepository.findAlunoIdAndStatusAndMateriaId(notas.getAluno().getId(), NotasStatusEnum.CONCLUIDO,
                notas.getMateria().getId()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe está materia para este aluno como concluída.");
        }

        if (notasRepository.findAlunoIdAndStatusAndDifferentMateriaId(notas.getAluno().getId(), NotasStatusEnum.NAO_CONCLUIDO,
                notas.getMateria().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O aluno ainda possui matérias em pendências para prosseguir para outra materia.");
        }

        notas.setStatus(NotasStatusEnum.NAO_CONCLUIDO);

        notasRepository.save(notas);

        List<Notas> alunoNotas = notasRepository.findAlunoIdAndStatusAndMateriaId(notas.getAluno().getId(), NotasStatusEnum.NAO_CONCLUIDO,
                notas.getMateria().getId());

        if (alunoNotas.size() >= 3) {
            if (alunoNotas.stream().mapToInt(Notas::getValor).sum() >= 80) {
                alunoNotas.forEach(item -> item.setStatus(NotasStatusEnum.CONCLUIDO));
                notasRepository.saveAll(alunoNotas);
            } else {
                notasRepository.deleteAll(alunoNotas);
            }
        }

        return notas;
    }

    public List<Notas> listar() {
        return notasRepository.findAll();
    }
}
