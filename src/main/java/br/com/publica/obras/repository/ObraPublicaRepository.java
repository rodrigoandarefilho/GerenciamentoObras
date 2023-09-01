package br.com.publica.obras.repository;

import br.com.publica.obras.domain.Obra.ObraPublica.ObraPublica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface ObraPublicaRepository extends JpaRepository<ObraPublica, BigDecimal> {
}