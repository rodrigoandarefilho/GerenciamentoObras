package br.com.publica.obras.domain.responsavel;

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
