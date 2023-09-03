package br.com.publica.obras.domain.model;

import br.com.publica.obras.domain.entity.Responsavel;

import java.math.BigDecimal;
import java.util.UUID;

public record DadosDetalhamentoResponsavel(UUID id, String nome, String cpf, BigDecimal codigo) {
    public DadosDetalhamentoResponsavel(Responsavel responsavel) {
        this(responsavel.getId(),
             responsavel.getNome(),
             responsavel.getCpf(),
             responsavel.getCodigo());
    }
}
