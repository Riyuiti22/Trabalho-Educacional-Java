package br.grupointegrado.Trabalho.Java.controller;

import br.grupointegrado.Trabalho.Java.dto.CursoRequestDTO;
import br.grupointegrado.Trabalho.Java.model.Curso;
import br.grupointegrado.Trabalho.Java.model.Disciplina;
import br.grupointegrado.Trabalho.Java.model.Professor;
import br.grupointegrado.Trabalho.Java.model.Turma;
import br.grupointegrado.Trabalho.Java.repository.CursoRepository;
import br.grupointegrado.Trabalho.Java.repository.DisciplinaRepository;
import br.grupointegrado.Trabalho.Java.repository.ProfessorRepository;
import br.grupointegrado.Trabalho.Java.repository.TurmaRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
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

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private ProfessorRepository professorRepository;

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

    @PutMapping("/{id}/atualizar-curso")
    public ResponseEntity<Curso> update(@PathVariable Integer id,
                                        @RequestBody CursoRequestDTO dto){
        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        curso.setNome(dto.nome());
        curso.setCodigo(dto.codigo());
        curso.setCarga_horaria(dto.carga_horaria());

        return ResponseEntity.ok(this.repository.save(curso));
    }

    @DeleteMapping("/{id}/deletar-curso")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        this.repository.delete(curso);
        return ResponseEntity.noContent().build();
    }
}
