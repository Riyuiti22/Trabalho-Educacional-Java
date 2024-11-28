package br.grupointegrado.Trabalho.Java.controller;

import br.grupointegrado.Trabalho.Java.dto.CursoRequestDTO;
import br.grupointegrado.Trabalho.Java.model.Curso;
import br.grupointegrado.Trabalho.Java.model.Professor;
import br.grupointegrado.Trabalho.Java.model.Turma;
import br.grupointegrado.Trabalho.Java.repository.CursoRepository;
import br.grupointegrado.Trabalho.Java.repository.ProfessorRepository;
import br.grupointegrado.Trabalho.Java.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoRepository repository;

    @Autowired
    private TurmaRepository turmaRepository;

    @GetMapping
    public ResponseEntity<List<Curso>> findAll(){
        return ResponseEntity.ok(this.repository.findAll());
    }

    @PostMapping
    public Curso save(@RequestBody CursoRequestDTO dto){
        Curso curso = new Curso();
        curso.setNome(dto.nome());
        curso.setCodigo(dto.codigo());
        curso.setCarga_horaria(dto.carga_horaria());

        return this.repository.save(curso);
    }

    @PostMapping("/{id}/add-turma")
    public ResponseEntity<Curso> addTurma(@PathVariable Integer id,
                                           @RequestBody Turma turma){
        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        turma.setCurso(curso);
        this.turmaRepository.save(turma);

        return ResponseEntity.ok(curso);
    }
}
