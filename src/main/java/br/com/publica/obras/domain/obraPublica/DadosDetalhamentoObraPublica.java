package br.com.publica.obras.domain.obraPublica;

import br.com.publica.obras.domain.responsavel.Responsavel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record DadosDetalhamentoObraPublica(UUID id,
                                           String descricao,
                                           LocalDate dataCadastro,
                                           LocalDate dataInicio,
                                           LocalDate dataFim,
                                           BigDecimal numero,
                                           List<Responsavel> responsaveis) {
    public DadosDetalhamentoObraPublica(ObraPublica obraPublica) {
        this(obraPublica.getId(),
             obraPublica.getDescricao(),
             obraPublica.getDataCadastro(),
             obraPublica.getDataInicio(),
             obraPublica.getDataFim(),
             obraPublica.getNumero(),
             obraPublica.getResponsaveis());
    }
}
