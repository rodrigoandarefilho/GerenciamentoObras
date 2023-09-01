package br.com.publica.obras.repository;

import br.com.publica.obras.domain.obra.Obra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ObraRepository extends JpaRepository<Obra, BigDecimal> {

    @Query("""
            select DISTINCT o from Obra o LEFT JOIN FETCH o.responsaveis or WHERE or.codigo = :codigo
            """)
    List<Obra> findAllObrasPorResponsavel(BigDecimal codigo);
}
