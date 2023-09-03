package br.com.publica.obras.domain.entity;

import br.com.publica.obras.utils.constantes.TipoDeZona;
import br.com.publica.obras.domain.dto.ObraPrivadaDTO;
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
public class ObraPrivadaEntity extends ObraEntity {

    @JoinColumn(name = "zona")
    @Enumerated(EnumType.STRING)
    private TipoDeZona zona;

    @JoinColumn(name = "area_total")
    private BigDecimal areaTotal;

    public ObraPrivadaEntity(ObraPrivadaDTO obraPrivadaDTO) {
        super(obraPrivadaDTO.dadosObra());
        this.zona = TipoDeZona.valueOf(obraPrivadaDTO.zona());
        this.areaTotal = obraPrivadaDTO.areaTotal();
    }
}
