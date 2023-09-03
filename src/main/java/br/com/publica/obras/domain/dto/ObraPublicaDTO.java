package br.com.publica.obras.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ObraPublicaDTO(

        @NotNull @Valid
        ObraDTO dadosObra,
        @NotNull(message = "{data_inicio.obrigatorio}")
        @Future
        LocalDate dataInicio,
        @NotNull(message = "{data_fim.obrigatorio}")
        LocalDate dataFim) {
}