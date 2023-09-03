package br.com.publica.obras.repository;

import br.com.publica.obras.domain.entity.ResponsavelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.UUID;

public interface ResponsavelRepository extends JpaRepository<ResponsavelEntity, UUID> {

    boolean existsByCodigo(BigDecimal codigo);

    ResponsavelEntity findByCodigo(BigDecimal codigo);
}
