package br.com.publica.obras.domain.model;

import br.com.publica.obras.domain.entity.ObraPrivada;
import br.com.publica.obras.domain.entity.Responsavel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record DadosDetalhamentoObraPrivada(UUID id,
                                           String descricao,
                                           LocalDate dataCadastro,
                                           String zona, BigDecimal areaTotal,
                                           BigDecimal numero,
                                           List<Responsavel> responsaveis) {
    public DadosDetalhamentoObraPrivada(ObraPrivada obraPrivada) {
        this(obraPrivada.getId(),
             obraPrivada.getDescricao(),
             obraPrivada.getDataCadastro(),
             String.valueOf(obraPrivada.getZona()),
             obraPrivada.getAreaTotal(),
             obraPrivada.getNumero(),
             obraPrivada.getResponsaveis());
    }
}
