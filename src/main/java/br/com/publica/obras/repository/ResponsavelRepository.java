package br.com.publica.obras.repository;

import br.com.publica.obras.domain.responsavel.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface ResponsavelRepository extends JpaRepository<Responsavel, BigDecimal> {

    @Query("""
            select r from Responsavel r
            where r.id = :id
            """)
    Responsavel findResponsavelById(BigDecimal id);

}
