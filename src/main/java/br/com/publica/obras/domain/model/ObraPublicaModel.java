package br.com.publica.obras.domain.model;

import br.com.publica.obras.domain.entity.ObraPublicaEntity;
import br.com.publica.obras.domain.entity.ResponsavelEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ObraPublicaModel(UUID id,
                               String descricao,
                               LocalDate dataCadastro,
                               LocalDate dataInicio,
                               LocalDate dataFim,
                               BigDecimal numero,
                               List<ResponsavelEntity> responsaveis) {
    public ObraPublicaModel(ObraPublicaEntity obraPublica) {
        this(obraPublica.getId(),
             obraPublica.getDescricao(),
             obraPublica.getDataCadastro(),
             obraPublica.getDataInicio(),
             obraPublica.getDataFim(),
             obraPublica.getNumero(),
             obraPublica.getResponsaveis());
    }
}
