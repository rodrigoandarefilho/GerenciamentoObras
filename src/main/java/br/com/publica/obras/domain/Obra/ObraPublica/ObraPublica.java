package br.com.publica.obras.domain.Obra.ObraPublica;

import br.com.publica.obras.domain.Obra.Obra;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ObraPublica extends Obra {

    @JoinColumn(name = "data_inicio")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;

    @JoinColumn(name = "data_fim")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFim;

    public ObraPublica(DadosCadastroObraPublica dadosCadastroObraPublica) {
        super(dadosCadastroObraPublica.dadosObra());
        this.dataInicio = dadosCadastroObraPublica.dataInicio();
        this.dataFim = dadosCadastroObraPublica.dataFim();
    }
}
