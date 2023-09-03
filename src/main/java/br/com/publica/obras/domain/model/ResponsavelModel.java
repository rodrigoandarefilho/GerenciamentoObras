package br.com.publica.obras.domain.model;

import br.com.publica.obras.domain.entity.ResponsavelEntity;

import java.math.BigDecimal;
import java.util.UUID;

public record ResponsavelModel(UUID id, String nome, String cpf, BigDecimal codigo) {
    public ResponsavelModel(ResponsavelEntity responsavel) {
        this(responsavel.getId(),
             responsavel.getNome(),
             responsavel.getCpf(),
             responsavel.getCodigo());
    }
}
