package br.grupointegrado.Trabalho.Java.repository;

import br.grupointegrado.Trabalho.Java.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository extends JpaRepository<Nota, Integer> {
}
