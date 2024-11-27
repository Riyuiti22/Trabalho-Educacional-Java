package br.grupointegrado.Trabalho.Java.repository;

import br.grupointegrado.Trabalho.Java.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
}
