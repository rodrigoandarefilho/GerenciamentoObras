package br.com.publica.obras.repository;

import br.com.publica.obras.domain.entity.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.UUID;

public interface ResponsavelRepository extends JpaRepository<Responsavel, UUID> {

    boolean existsByCodigo(BigDecimal codigo);

    Responsavel findByCodigo(BigDecimal codigo);
}
