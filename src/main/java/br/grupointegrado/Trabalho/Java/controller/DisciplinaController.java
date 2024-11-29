package br.grupointegrado.Trabalho.Java.controller;

import br.grupointegrado.Trabalho.Java.dto.DisciplinaRequestDTO;
import br.grupointegrado.Trabalho.Java.dto.ProfessorRequestDTO;
import br.grupointegrado.Trabalho.Java.model.Disciplina;
import br.grupointegrado.Trabalho.Java.model.Professor;
import br.grupointegrado.Trabalho.Java.repository.CursoRepository;
import br.grupointegrado.Trabalho.Java.repository.DisciplinaRepository;
import br.grupointegrado.Trabalho.Java.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Autowired
    ProfessorRepository professorRepository;


    @GetMapping
    public ResponseEntity<List<Disciplina>> findAll() {
        return ResponseEntity.ok(this.disciplinaRepository.findAll());
    }

    @PostMapping("/{id}/add-disciplina")
    public ResponseEntity<Professor> addDisciplina(@PathVariable Integer id,
                                                   @RequestBody Disciplina disciplina){
        Professor professor = this.professorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));
        disciplina.setProfessor(professor);
        this.disciplinaRepository.save(disciplina);

        return ResponseEntity.ok(professor);
    }

}
