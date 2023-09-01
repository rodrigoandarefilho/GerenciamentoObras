package br.com.publica.obras.domain.obra;

import br.com.publica.obras.domain.responsavel.Responsavel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosObra(
        @NotBlank(message = "{descricao.obrigatorio}")
        String descricao,
        @NotNull(message = "{responsavel.obrigatorio}")
        List<Responsavel> responsaveis) {
}
