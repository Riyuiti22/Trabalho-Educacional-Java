package br.grupointegrado.Trabalho.Java.controller;

import br.grupointegrado.Trabalho.Java.model.Aluno;
import br.grupointegrado.Trabalho.Java.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AlunoController {

    @Autowired
    private AlunoRepository repository;


    @GetMapping("/alunos")
    public List<Aluno> findAll() {
        return this.repository.findAll();
    }
}
