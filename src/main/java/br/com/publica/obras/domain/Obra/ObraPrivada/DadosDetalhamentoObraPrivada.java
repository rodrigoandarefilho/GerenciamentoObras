package br.com.publica.obras.domain.Obra.ObraPrivada;

import br.com.publica.obras.domain.responsavel.Responsavel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record DadosDetalhamentoObraPrivada(BigDecimal id,
                                           String descricao,
                                           LocalDate dataCadastro,
                                           String zona, BigDecimal areaTotal,
                                           List<Responsavel> responsaveis) {
    public DadosDetalhamentoObraPrivada(ObraPrivada obraPrivada) {
        this(obraPrivada.getId(),
             obraPrivada.getDescricao(),
             obraPrivada.getDataCadastro(),
             String.valueOf(obraPrivada.getZona()),
             obraPrivada.getAreaTotal(),
             obraPrivada.getResponsaveis());
    }
}
