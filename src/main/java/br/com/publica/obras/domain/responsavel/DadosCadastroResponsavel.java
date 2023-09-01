package br.com.publica.obras.domain.responsavel;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroResponsavel(
        @NotBlank(message = "{nome.obrigatorio}")
        String nome,
        @NotBlank(message = "{cpf.obrigatorio}")
        String cpf
) {
}
