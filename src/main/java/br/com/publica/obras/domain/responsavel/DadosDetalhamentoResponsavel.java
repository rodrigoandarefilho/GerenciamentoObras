package br.com.publica.obras.domain.responsavel;

import java.math.BigDecimal;

public record DadosDetalhamentoResponsavel(BigDecimal id, String nome, String cpf, BigDecimal codigo) {
    public DadosDetalhamentoResponsavel (Responsavel responsavel){
        this(responsavel.getId(),
             responsavel.getNome(),
             responsavel.getCpf(),
             responsavel.getCodigo());
    }
}
