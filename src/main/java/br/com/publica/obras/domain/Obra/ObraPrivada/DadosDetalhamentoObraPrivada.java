package br.com.publica.obras.domain.Obra.ObraPrivada;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosDetalhamentoObraPrivada(BigDecimal id, String descricao, LocalDate dataCadastro, String zona, BigDecimal areaTotal) {
    public DadosDetalhamentoObraPrivada(ObraPrivada obraPrivada) {
        this(obraPrivada.getId(),
             obraPrivada.getDescricao(),
             obraPrivada.getDataCadastro(),
             String.valueOf(obraPrivada.getZona()),
             obraPrivada.getAreaTotal());
    }
}
