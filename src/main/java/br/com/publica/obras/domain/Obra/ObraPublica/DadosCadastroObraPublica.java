package br.com.publica.obras.domain.Obra.ObraPublica;

import br.com.publica.obras.domain.Obra.DadosObra;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastroObraPublica(
        DadosObra dadosObra,
        @NotNull(message = "{data_inicio.obrigatorio}")
        @Future
        LocalDate dataInicio,
        @NotNull(message = "{data_fim.obrigatorio}")
        LocalDate dataFim) {
}