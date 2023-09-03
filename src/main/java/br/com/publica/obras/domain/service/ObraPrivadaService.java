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

    /**
     *@param obraPrivadaDTO Recebe um objeto do tipo obra privada, contendo os codigos dos responsaveis
     *@return Salva no banco a obra privada e envia para controller o mesmo
     */
    public ObraPrivadaEntity cadastrarObraPrivada(ObraPrivadaDTO obraPrivadaDTO) {
        var obraPrivada = new ObraPrivadaEntity(obraPrivadaDTO);

        var listaDeCodigosResponsaveis = obraPrivadaDTO.dadosObra().responsaveis();
        obraPrivada.setResponsaveis(responsavelService.gerarListaDeResponsaveis(listaDeCodigosResponsaveis));
        return obraPrivadaRepository.save(obraPrivada);
    }

    /**
     *@param id Recebe o ID de obra privada
     *@return Realiza uma consulta no banco e retorna a obra privada filtrada pelo ID
     */
    public ObraPrivadaModel buscarObraPrivadaPorId(UUID id) {
        return new ObraPrivadaModel(obraPrivadaRepository.getReferenceById(id));
    }
}
