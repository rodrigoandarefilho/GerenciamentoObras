package br.com.publica.obras.domain.model;

import br.com.publica.obras.domain.entity.ObraPrivadaEntity;
import br.com.publica.obras.domain.entity.ResponsavelEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ObraPrivadaModel(UUID id,
                               String descricao,
                               @JsonFormat(pattern = "dd/MM/yyyy")
                               LocalDate dataCadastro,
                               String zona, BigDecimal areaTotal,
                               BigDecimal numero,
                               List<ResponsavelEntity> responsaveis) {
    public ObraPrivadaModel(ObraPrivadaEntity obraPrivada) {
        this(obraPrivada.getId(),
             obraPrivada.getDescricao(),
             obraPrivada.getDataCadastro(),
             String.valueOf(obraPrivada.getZona()),
             obraPrivada.getAreaTotal(),
             obraPrivada.getNumero(),
             obraPrivada.getResponsaveis());
    }
}
