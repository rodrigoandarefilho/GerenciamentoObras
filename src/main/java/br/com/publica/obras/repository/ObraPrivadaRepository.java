package br.com.publica.obras.repository;

import br.com.publica.obras.domain.Obra.ObraPrivada.ObraPrivada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface ObraPrivadaRepository extends JpaRepository<ObraPrivada, BigDecimal> {
}