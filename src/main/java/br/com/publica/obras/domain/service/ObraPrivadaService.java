package br.com.publica.obras.domain.service;

import br.com.publica.obras.domain.dto.ObraPrivadaDTO;
import br.com.publica.obras.domain.entity.ObraPrivadaEntity;
import br.com.publica.obras.domain.model.ObraPrivadaModel;
import br.com.publica.obras.repository.ObraPrivadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ObraPrivadaService {

    @Autowired
    private ObraPrivadaRepository obraPrivadaRepository;
    @Autowired
    private ResponsavelService responsavelService;

    public ObraPrivadaEntity cadastrarObraPrivada(ObraPrivadaDTO obraPrivadaDTO) {
        var obraPrivada = new ObraPrivadaEntity(obraPrivadaDTO);

        var listaDeCodigosResponsaveis = obraPrivadaDTO.dadosObra().responsaveis();
        obraPrivada.setResponsaveis(responsavelService.gerarListaDeResponsaveis(listaDeCodigosResponsaveis));
        return obraPrivadaRepository.save(obraPrivada);
    }
    public ObraPrivadaModel buscarObraPrivadaPorNumero(UUID id) {
        return new ObraPrivadaModel(obraPrivadaRepository.getReferenceById(id));
    }
}
