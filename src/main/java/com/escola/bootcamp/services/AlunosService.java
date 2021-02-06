package com.escola.bootcamp.services;

import com.escola.bootcamp.entities.Alunos;
import com.escola.bootcamp.objetos.HistoricoAlunos;
import com.escola.bootcamp.objetos.MelhoresAlunos;
import com.escola.bootcamp.repositories.AlunosRepository;
import com.escola.bootcamp.repositories.NotasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AlunosService {

    private final AlunosRepository alunosRepository;
    private final NotasRepository notasRepository;

    public Alunos criar(Alunos alunos) {
        if (alunosRepository.findByNome(alunos.getNome()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe um aluno com este nome.");
        }
        return alunosRepository.save(alunos);
    }

    @Transactional(readOnly = true)
    public List<Alunos> listar() {
        return alunosRepository.findAll();
    }

    public Alunos listarPorId(Long id) {
        Alunos aluno = alunosRepository.findById(id).orElse(null);
        if(aluno == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aluno não encontrado.");
        }
        return aluno;
    }

    public List<HistoricoAlunos> historico() {
        List<Alunos> alunos = alunosRepository.findAll();
        List<HistoricoAlunos> listaHistoricoAlunos = new ArrayList<>();
        alunos.forEach(aluno -> {
            HistoricoAlunos historicoAlunos = new HistoricoAlunos();
            historicoAlunos.setAluno(aluno);
            historicoAlunos.setNotas(notasRepository.findAllByAlunoId(aluno.getId()));
            listaHistoricoAlunos.add(historicoAlunos);
        });
        return listaHistoricoAlunos;
    }

    public HistoricoAlunos historicoPorId(Long id) {
        Alunos aluno = alunosRepository.findById(id).orElse(null);
        if(aluno == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aluno não encontrado.");
        }
        HistoricoAlunos historicoAlunos = new HistoricoAlunos();
        historicoAlunos.setAluno(aluno);
        historicoAlunos.setNotas(notasRepository.findAllByAlunoId(id));
        return historicoAlunos;
    }

    public List<MelhoresAlunos> melhoresAlunos() {
        List<Alunos> alunos = alunosRepository.findAll();
        List<MelhoresAlunos> listaMelhoresAlunos = new ArrayList<>();
        alunos.forEach(aluno -> {
            MelhoresAlunos melhoresAlunos = new MelhoresAlunos();
            melhoresAlunos.setAluno(aluno);
            melhoresAlunos.setScore(notasRepository.sumValorAlunos(aluno.getId()));
            listaMelhoresAlunos.add(melhoresAlunos);
        });
        listaMelhoresAlunos.sort(Comparator.comparingInt(MelhoresAlunos::getScore).reversed());
        return listaMelhoresAlunos;
    }
}
