package br.com.publica.obras.domain.responsavel;

import br.com.publica.obras.domain.Obra.Obra;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Responsavel")
@Table(name = "responsavel")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Responsavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;
    private String nome;
    private String cpf;
    @ManyToMany(mappedBy = "responsaveis",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Obra> obras = new ArrayList<>();

    public Responsavel(DadosCadastroResponsavel dadosCadastroResponsavel) {
        this.nome = dadosCadastroResponsavel.nome();
        this.cpf = dadosCadastroResponsavel.cpf();
    }
}
