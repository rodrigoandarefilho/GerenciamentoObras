package br.com.publica.obras.domain.responsavel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosCadastroResponsavel(
        @NotNull(message = "{codigo.obrigatorio}")
        BigDecimal codigo,
        @NotBlank(message = "{nome.obrigatorio}")
        String nome,
        @NotBlank(message = "{cpf.obrigatorio}")
        String cpf) {
}
