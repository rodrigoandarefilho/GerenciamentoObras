package br.com.publica.obras.repository;

import br.com.publica.obras.domain.responsavel.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ResponsavelRepository extends JpaRepository<Responsavel, BigDecimal> {

    @Query("""
            select m from Responsavel m
            where m.id = :id
            """)
    Responsavel findAllMedicosAtivos(BigDecimal id);

}
