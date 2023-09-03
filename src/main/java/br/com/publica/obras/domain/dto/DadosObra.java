package br.com.publica.obras.domain.dto;

import br.com.publica.obras.domain.entity.Responsavel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record DadosObra(
        @NotBlank(message = "{descricao.obrigatorio}")
        String descricao,
        @NotNull(message = "{responsavel.obrigatorio}")
        List<Responsavel> responsaveis,
        @NotNull(message = "{numero_obra.obrigatorio}")
        BigDecimal numero) {
}
