package br.com.publica.obras.repository;

import br.com.publica.obras.domain.entity.ObraPrivadaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ObraPrivadaRepository extends JpaRepository<ObraPrivadaEntity, UUID> {
}
