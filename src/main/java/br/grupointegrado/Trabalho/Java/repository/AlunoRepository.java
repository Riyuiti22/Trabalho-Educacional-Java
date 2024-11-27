package br.grupointegrado.Trabalho.Java.repository;

import br.grupointegrado.Trabalho.Java.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
}
