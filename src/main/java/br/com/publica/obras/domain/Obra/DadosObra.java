package br.com.publica.obras.domain.Obra;

import java.math.BigDecimal;
import java.util.List;

public record DadosObra(String descricao, List<BigDecimal> responsaveis) {
}
