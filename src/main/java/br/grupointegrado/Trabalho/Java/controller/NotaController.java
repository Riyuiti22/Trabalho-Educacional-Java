package br.grupointegrado.Trabalho.Java.controller;

import br.grupointegrado.Trabalho.Java.dto.NotaRequestDTO;
import br.grupointegrado.Trabalho.Java.model.Disciplina;
import br.grupointegrado.Trabalho.Java.model.Matricula;
import br.grupointegrado.Trabalho.Java.model.Nota;
import br.grupointegrado.Trabalho.Java.repository.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notas")
public class NotaController {

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Autowired
    MatriculaRepository matriculaRepository;

    @Autowired
    NotaRepository notaRepository;

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    TurmaRepository turmaRepository;

    @GetMapping
    public ResponseEntity<List<Nota>> findAll() {
        return ResponseEntity.ok(this.notaRepository.findAll());
    }

    @PostMapping("/{id}/add-nota")
    public ResponseEntity<Nota> addNota(@RequestBody NotaRequestDTO dto) {
        Matricula matricula = this.matriculaRepository.findById(dto.matriculaId())
                .orElseThrow(() -> new IllegalArgumentException("Matricula não existe"));
        Disciplina disciplina = this.disciplinaRepository.findById(dto.disciplinaId())
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não existe"));

        Nota nota = new Nota();
        nota.setNota(dto.nota());
        nota.setData_lancamento(dto.data_lancamento());
        nota.setMatricula(matricula);
        nota.setDisciplina(disciplina);
        notaRepository.save(nota);

        return ResponseEntity.ok(nota);
    }

    @PutMapping("/{id}/ataulizar-nota")
    public ResponseEntity<Nota> update(@PathVariable Integer id,
                                       @RequestBody NotaRequestDTO dto) {
        Nota nota = this.notaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrado"));
        Matricula matricula = this.matriculaRepository.findById(dto.matriculaId())
                .orElseThrow(() -> new IllegalArgumentException("Matricula não existe"));
        Disciplina disciplina = this.disciplinaRepository.findById(dto.disciplinaId())
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não existe"));

        nota.setNota(dto.nota());
        nota.setData_lancamento(dto.data_lancamento());
        nota.setMatricula(matricula);
        nota.setDisciplina(disciplina);
        notaRepository.save(nota);

        return ResponseEntity.ok(this.notaRepository.save(nota));
    }

    @DeleteMapping("/{id}/deletar-nota")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Nota nota = this.notaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrado"));

        this.notaRepository.delete(nota);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Obter boletim de um aluno")
    @GetMapping("/boletim/{alunoId}")
    public ResponseEntity<List<Map<String, Object>>> getBoletimAluno(@PathVariable Integer alunoId) {
        List<Map<String, Object>> boletim = notaRepository.findBoletimByAlunoId(alunoId);
        return ResponseEntity.ok(boletim);
    }

    @Operation(summary = "Obter notas por turma")
    @GetMapping("/turma/{turmaId}")
    public ResponseEntity<List<Map<String, Object>>> getNotasPorTurma(@PathVariable Integer turmaId) {
        List<Map<String, Object>> notas = notaRepository.findNotasByTurmaId(turmaId);
        return ResponseEntity.ok(notas);
    }

    @Operation(summary = "Obter notas por disciplina")
    @GetMapping("/disciplina/{disciplinaId}")
    public ResponseEntity<List<Map<String, Object>>> getNotasPorDisciplina(@PathVariable Integer disciplinaId) {
        List<Map<String, Object>> notas = notaRepository.findNotasByDisciplinaId(disciplinaId);
        return ResponseEntity.ok(notas);
    }

    @Operation(summary = "Obter média das notas por curso")
    @GetMapping("/media/curso")
    public ResponseEntity<List<Map<String, Object>>> getMediaNotasPorCurso() {
        List<Map<String, Object>> medias = notaRepository.findMediaNotasByCurso();
        return ResponseEntity.ok(medias);
    }

    @Operation(summary = "Obter média das notas por aluno")
    @GetMapping("/media/aluno")
    public ResponseEntity<List<Map<String, Object>>> getMediaNotasPorAluno() {
        List<Map<String, Object>> mediaAluno = notaRepository.findMediaNotasByAluno();
        return ResponseEntity.ok(mediaAluno);
    }
}