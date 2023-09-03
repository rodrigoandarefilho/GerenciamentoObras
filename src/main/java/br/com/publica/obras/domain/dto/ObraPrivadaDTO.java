package br.com.publica.obras.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ObraPrivadaDTO(
        @NotNull @Valid
        ObraDTO dadosObra,
        @NotBlank(message = "{zona.obrigatorio}")
        String zona,
        @NotNull(message = "{area_total.obrigatorio}")
        BigDecimal areaTotal) {
}
