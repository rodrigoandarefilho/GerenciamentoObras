package br.com.publica.obras.domain.obra.obraPrivada;

import br.com.publica.obras.domain.obra.Obra;
import br.com.publica.obras.domain.constantes.TipoDeZona;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("privada")
public class ObraPrivada extends Obra {

    @JoinColumn(name = "zona")
    @Enumerated(EnumType.STRING)
    private TipoDeZona zona;

    @JoinColumn(name = "area_total")
    private BigDecimal areaTotal;

    public ObraPrivada(DadosCadastroObraPrivada dadosCadastroObraPrivada) {
        super(dadosCadastroObraPrivada.dadosObra());
        this.zona = TipoDeZona.valueOf(dadosCadastroObraPrivada.zona());
        this.areaTotal = dadosCadastroObraPrivada.areaTotal();
    }
}
