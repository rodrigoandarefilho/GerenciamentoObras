package br.com.publica.obras.domain.dto.responsavel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public record ResponsavelDTO(
        @NotNull(message = "{codigo.obrigatorio}")
        BigDecimal codigo,
        @NotBlank(message = "{nome.obrigatorio}")
        String nome,
        @NotBlank(message = "{cpf.obrigatorio}")
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}", message = "{cpf.invalido}")
        String cpf) {
}
