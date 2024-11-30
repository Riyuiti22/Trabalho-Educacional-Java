package br.grupointegrado.Trabalho.Java.dto;

import java.math.BigDecimal;
import java.util.Date;

public record NotaRequestDTO(Integer matriculaId, Integer disciplinaId, Double nota, Date data_lancamento) {
}
