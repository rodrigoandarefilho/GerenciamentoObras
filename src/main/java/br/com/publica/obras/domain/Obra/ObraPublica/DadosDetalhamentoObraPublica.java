package br.com.publica.obras.domain.Obra.ObraPublica;

import br.com.publica.obras.domain.responsavel.Responsavel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record DadosDetalhamentoObraPublica(BigDecimal id,
                                           String descricao,
                                           LocalDate dataCadastro,
                                           LocalDate dataInicio,
                                           LocalDate dataFim,
                                           List<Responsavel> responsaveis) {
    public DadosDetalhamentoObraPublica(ObraPublica obraPublica) {
        this(obraPublica.getId(),
             obraPublica.getDescricao(),
             obraPublica.getDataCadastro(),
             obraPublica.getDataInicio(),
             obraPublica.getDataFim(),
             obraPublica.getResponsaveis());
    }
}
