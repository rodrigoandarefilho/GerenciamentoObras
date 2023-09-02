package br.com.publica.obras.repository;

import br.com.publica.obras.domain.responsavel.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.UUID;

public interface ResponsavelRepository extends JpaRepository<Responsavel, UUID> {

    @Query("""
            select r from Responsavel r
            where r.codigo = :codigo
            """)
    Responsavel findResponsavelById(BigDecimal codigo);

    boolean existsByCodigo(BigDecimal codigo);

    Responsavel findByCodigo(BigDecimal codigo);
}
