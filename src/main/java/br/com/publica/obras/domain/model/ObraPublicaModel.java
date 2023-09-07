package br.com.publica.obras.domain.model;

import br.com.publica.obras.domain.entity.ObraPublicaEntity;
import br.com.publica.obras.domain.entity.ResponsavelEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ObraPublicaModel(UUID id,
                               String descricao,
                               @JsonFormat(pattern = "dd/MM/yyyy")
                               LocalDate dataCadastro,
                               @JsonFormat(pattern = "dd/MM/yyyy")
                               LocalDate dataInicio,
                               @JsonFormat(pattern = "dd/MM/yyyy")
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
