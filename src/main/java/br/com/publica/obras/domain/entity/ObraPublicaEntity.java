package br.com.publica.obras.domain.entity;

import br.com.publica.obras.domain.dto.ObraPublicaDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.DiscriminatorValue;
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
@DiscriminatorValue("publica")
public class ObraPublicaEntity extends ObraEntity {

    @JoinColumn(name = "data_inicio")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;

    @JoinColumn(name = "data_fim")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFim;

    public ObraPublicaEntity(ObraPublicaDTO obraPublicaDTO) {
        super(obraPublicaDTO.dadosObra());
        this.dataInicio = obraPublicaDTO.dataInicio();
        this.dataFim = obraPublicaDTO.dataFim();
    }
}
