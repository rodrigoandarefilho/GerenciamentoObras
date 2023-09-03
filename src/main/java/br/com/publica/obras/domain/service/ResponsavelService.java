package br.com.publica.obras.domain.service;

import br.com.publica.obras.domain.dto.responsavel.CodigoResponsavelDTO;
import br.com.publica.obras.domain.dto.responsavel.ResponsavelDTO;
import br.com.publica.obras.domain.entity.ResponsavelEntity;
import br.com.publica.obras.domain.model.ResponsavelModel;
import br.com.publica.obras.infra.exception.ValidacaoException;
import br.com.publica.obras.repository.ResponsavelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ResponsavelService {

    @Autowired
    private ResponsavelRepository responsavelRepository;

    /**
     *@param responsavelDTO Recebe um objeto do tipo responsável
     *@return Salva no banco o responsável e envia para controller o mesmo
     */
    public ResponsavelEntity cadastrarResponsavel(ResponsavelDTO responsavelDTO) {
        var responsavel = new ResponsavelEntity(responsavelDTO);
        return responsavelRepository.save(responsavel);
    }

    /**
     *@param id Recebe o ID do responsável
     *@return Realiza uma consulta no banco e retorna o responsável pelo ID
     */
    public ResponsavelModel buscarResponsavelPorId(UUID id) {
        return new ResponsavelModel(responsavelRepository.getReferenceById(id));
    }

    /**
     *@param listaDeCodigosResponsaveis Recebe uma lista de codigos de responsaveis
     *@return Retorna uma lista de responsáveis (objeto)
     */
    public List<ResponsavelEntity> gerarListaDeResponsaveis(List<CodigoResponsavelDTO> listaDeCodigosResponsaveis) {
        List<ResponsavelEntity> listaCompletaDeResponsaveis = new ArrayList<>();
        for (int i = 0; i < listaDeCodigosResponsaveis.size(); i++) {
            if (!responsavelRepository.existsByCodigo(listaDeCodigosResponsaveis.get(i).codigo())) {
                throw new ValidacaoException("Não tem nenhum responsável cadastrado com o código " + listaDeCodigosResponsaveis.get(i).codigo());
            }
            var responsavel = responsavelRepository.findByCodigo(listaDeCodigosResponsaveis.get(i).codigo());
            listaCompletaDeResponsaveis.add(responsavel);
        }
        return listaCompletaDeResponsaveis;
    }
}
