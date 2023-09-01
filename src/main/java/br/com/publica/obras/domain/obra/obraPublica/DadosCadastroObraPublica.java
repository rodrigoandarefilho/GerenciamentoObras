package br.com.publica.obras.domain.obra.obraPublica;

import br.com.publica.obras.domain.obra.DadosObra;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastroObraPublica(
        @NotNull @Valid
        DadosObra dadosObra,
        @NotNull(message = "{data_inicio.obrigatorio}")
        @Future
        LocalDate dataInicio,
        @NotNull(message = "{data_fim.obrigatorio}")
        LocalDate dataFim) {
}