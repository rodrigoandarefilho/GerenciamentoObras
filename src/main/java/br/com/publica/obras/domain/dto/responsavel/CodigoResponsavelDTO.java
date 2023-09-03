package br.com.publica.obras.domain.dto.responsavel;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CodigoResponsavelDTO(
        @NotNull(message = "{codigo.obrigatorio}")
        BigDecimal codigo
) {
}
