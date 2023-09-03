package br.com.publica.obras.domain.dto;

import br.com.publica.obras.domain.entity.ResponsavelEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record ObraDTO(
        @NotBlank(message = "{descricao.obrigatorio}")
        String descricao,
        @NotNull(message = "{responsavel.obrigatorio}")
        List<ResponsavelEntity> responsaveis,
        @NotNull(message = "{numero_obra.obrigatorio}")
        BigDecimal numero) {
}
