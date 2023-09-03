package br.com.publica.obras.domain.service;

import br.com.publica.obras.domain.dto.ObraPublicaDTO;
import br.com.publica.obras.domain.entity.ObraPublicaEntity;
import br.com.publica.obras.domain.model.ObraPublicaModel;
import br.com.publica.obras.repository.ObraPublicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ObraPublicaService {

    @Autowired
    private ObraPublicaRepository obraPublicaRepository;
    @Autowired
    private ResponsavelService responsavelService;

    public ObraPublicaEntity cadastrarObraPublica(ObraPublicaDTO obraPublicaDTO) {
        var obraPublica = new ObraPublicaEntity(obraPublicaDTO);
        var listaDeCodigosResponsaveis = obraPublicaDTO.dadosObra().responsaveis();
        obraPublica.setResponsaveis(responsavelService.gerarListaDeResponsaveis(listaDeCodigosResponsaveis));
        return obraPublicaRepository.save(obraPublica);
    }

    public ObraPublicaModel buscarObraPrivadaPorID(UUID id) {
        return new ObraPublicaModel(obraPublicaRepository.getReferenceById(id));
    }
}
