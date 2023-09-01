package br.com.publica.obras.domain.Obra.ObraPublica;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosDetalhamentoObraPublica(BigDecimal id, String descricao, LocalDate dataCadastro, LocalDate dataInicio, LocalDate dataFim) {
    public DadosDetalhamentoObraPublica(ObraPublica obraPublica) {
        this(obraPublica.getId(),
             obraPublica.getDescricao(),
             obraPublica.getDataCadastro(),
             obraPublica.getDataInicio(),
             obraPublica.getDataFim());
    }
}
