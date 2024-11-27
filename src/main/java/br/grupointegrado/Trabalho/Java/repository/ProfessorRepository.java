package br.grupointegrado.Trabalho.Java.repository;

import br.grupointegrado.Trabalho.Java.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}
