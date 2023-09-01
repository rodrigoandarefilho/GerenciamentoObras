package br.com.publica.obras.domain.Obra;

import br.com.publica.obras.domain.responsavel.Responsavel;

import java.math.BigDecimal;
import java.util.List;

public record DadosObra(String descricao, List<Responsavel> responsaveis) {
}
