package br.com.publica.obras.repository;

import br.com.publica.obras.domain.entity.ObraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ObraRepository extends JpaRepository<ObraEntity, UUID> {

    @Query("""
            select DISTINCT o from Obra o LEFT JOIN FETCH o.responsaveis or WHERE or.codigo = :codigo
            """)
    List<ObraEntity> findAllObrasPorResponsavel(BigDecimal codigo);
}
