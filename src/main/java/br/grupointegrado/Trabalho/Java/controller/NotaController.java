package br.grupointegrado.Trabalho.Java.controller;

import br.grupointegrado.Trabalho.Java.dto.NotaRequestDTO;
import br.grupointegrado.Trabalho.Java.model.Disciplina;
import br.grupointegrado.Trabalho.Java.model.Matricula;
import br.grupointegrado.Trabalho.Java.model.Nota;
import br.grupointegrado.Trabalho.Java.repository.DisciplinaRepository;
import br.grupointegrado.Trabalho.Java.repository.MatriculaRepository;
import br.grupointegrado.Trabalho.Java.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notas")
public class NotaController {

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Autowired
    MatriculaRepository matriculaRepository;

    @Autowired
    NotaRepository notaRepository;

    @GetMapping
    public ResponseEntity<List<Nota>> findAll() {
        return ResponseEntity.ok(this.notaRepository.findAll());
    }

    @PostMapping("/{id}/add-nota")
    public ResponseEntity<Nota> addNota(@RequestBody NotaRequestDTO dto){
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
}
