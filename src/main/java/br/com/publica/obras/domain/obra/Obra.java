package br.com.publica.obras.domain.obra;

import br.com.publica.obras.domain.responsavel.Responsavel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "Obra")
@Table(name = "obra")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "tipo")
public abstract class Obra {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JoinColumn(name = "data_cadastro")
    private LocalDate dataCadastro;
    private String descricao;
    private BigDecimal numero;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "obra_responsavel",
            joinColumns = @JoinColumn(name = "obra_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "responsavel_id", referencedColumnName = "id"))
    @JsonManagedReference
    private List<Responsavel> responsaveis = new ArrayList<>();

    public Obra(DadosObra dadosObra) {
        this.descricao = dadosObra.descricao();
        this.dataCadastro = LocalDate.now();
        this.responsaveis = dadosObra.responsaveis();
        this.numero = dadosObra.numero();
    }
}
