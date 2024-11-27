package br.grupointegrado.Trabalho.Java.controller;

import br.grupointegrado.Trabalho.Java.dto.AlunoRequestDTO;
import br.grupointegrado.Trabalho.Java.dto.ProfessorRequestDTO;
import br.grupointegrado.Trabalho.Java.model.Aluno;
import br.grupointegrado.Trabalho.Java.model.Professor;
import br.grupointegrado.Trabalho.Java.repository.AlunoRepository;
import br.grupointegrado.Trabalho.Java.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    @Autowired
    private ProfessorRepository repository;


    @GetMapping
    public ResponseEntity<List<Professor>> findAll() {
    //    return this.repository.findAll();
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> findById(@PathVariable Integer id){
    //    return this.repository.findById(id)
    //            .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
        Professor professor = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        return ResponseEntity.ok(professor);
    }

    @PostMapping
    public Professor save(@RequestBody ProfessorRequestDTO dto){
        Professor professor = new Professor();
        professor.setNome(dto.nome());
        professor.setEmail(dto.email());
        professor.setTelefone(dto.telefone());
        professor.setEspecialidade(dto.especialidade());

        return this.repository.save(professor);
    }

    @PutMapping("/{id}")
    public Professor update(@PathVariable Integer id,
                        @RequestBody ProfessorRequestDTO dto){
        Professor professor = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        professor.setNome(dto.nome());
        professor.setEmail(dto.email());
        professor.setTelefone(dto.telefone());
        professor.setEspecialidade(dto.especialidade());

        return this.repository.save(professor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Professor professor = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));
        this.repository.delete(professor);

        return ResponseEntity.noContent().build();
    }
}
