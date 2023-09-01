package br.com.publica.obras.domain.Obra.ObraPrivada;

import br.com.publica.obras.domain.Obra.DadosObra;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosCadastroObraPrivada(
        DadosObra dadosObra,
        @NotBlank(message = "{zona.obrigatorio}")
        String zona,
        @NotNull(message = "{area_total.obrigatorio}")
        BigDecimal areaTotal) {
}
