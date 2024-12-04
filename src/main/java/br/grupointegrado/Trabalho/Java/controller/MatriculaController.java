package br.grupointegrado.Trabalho.Java.controller;

import br.grupointegrado.Trabalho.Java.dto.MatriculaRequestDTO;
import br.grupointegrado.Trabalho.Java.model.Aluno;
import br.grupointegrado.Trabalho.Java.model.Matricula;
import br.grupointegrado.Trabalho.Java.model.Turma;
import br.grupointegrado.Trabalho.Java.repository.AlunoRepository;
import br.grupointegrado.Trabalho.Java.repository.MatriculaRepository;
import br.grupointegrado.Trabalho.Java.repository.TurmaRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matricula")
public class MatriculaController {

    @Autowired
    MatriculaRepository matriculaRepository;

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    TurmaRepository turmaRepository;

    @GetMapping
    public ResponseEntity<List<Matricula>> findAll(){
        return  ResponseEntity.ok(this.matriculaRepository.findAll());
    }

    @PostMapping("/{id}/add-matricula")
    public ResponseEntity<Matricula> addMatricula(@RequestBody MatriculaRequestDTO dto){
        Aluno aluno = this.alunoRepository.findById(dto.alunoId())
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
        Turma turma = this.turmaRepository.findById(dto.turmaId())
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrado"));

        Matricula matricula = new Matricula();
        matricula.setAluno(aluno);
        matricula.setTurma(turma);
        matriculaRepository.save(matricula);

        return ResponseEntity.ok(matricula);
    }

    @PutMapping("/{id}/atualizar-matricula")
    public ResponseEntity<Matricula> update(@PathVariable Integer id,
                                            @RequestBody MatriculaRequestDTO dto){
        Matricula matricula = this.matriculaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matricula não encontrada"));
        Aluno aluno = this.alunoRepository.findById(dto.alunoId())
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
        Turma turma = this.turmaRepository.findById(dto.turmaId())
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrado"));

        matricula.setAluno(aluno);
        matricula.setTurma(turma);

        return ResponseEntity.ok(this.matriculaRepository.save(matricula));
    }

    @DeleteMapping("{id}/deletar-matricula")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        Matricula matricula = this.matriculaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matricula não encontrado"));

        this.matriculaRepository.delete(matricula);
        return ResponseEntity.noContent().build();
    }
}
