package br.com.publica.obras.domain.entity;

import br.com.publica.obras.domain.dto.ResponsavelDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "Responsavel")
@Table(name = "responsavel")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ResponsavelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String cpf;
    @ManyToMany(mappedBy = "responsaveis",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<ObraEntity> obraEntities = new ArrayList<>();
    private BigDecimal codigo;

    public ResponsavelEntity(ResponsavelDTO responsavelDTO) {
        this.nome = responsavelDTO.nome();
        this.cpf = responsavelDTO.cpf();
        this.codigo = responsavelDTO.codigo();
    }
}
