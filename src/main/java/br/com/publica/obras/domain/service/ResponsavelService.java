package br.com.publica.obras.domain.service;

import br.com.publica.obras.domain.dto.ResponsavelDTO;
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

    public ResponsavelEntity cadastrarResponsavel(ResponsavelDTO responsavelDTO) {
        var responsavel = new ResponsavelEntity(responsavelDTO);
        return responsavelRepository.save(responsavel);
    }

    public ResponsavelModel buscarResponsavelPorId(UUID id) {
        return new ResponsavelModel(responsavelRepository.getReferenceById(id));
    }

    public List<ResponsavelEntity> gerarListaDeResponsaveis(List<ResponsavelEntity> listaDeCodigosResponsaveis) {
        List<ResponsavelEntity> listaCompletaDeResponsaveis = new ArrayList<>();
        for (int i = 0; i < listaDeCodigosResponsaveis.size(); i++) {
            if (!responsavelRepository.existsByCodigo(listaDeCodigosResponsaveis.get(i).getCodigo())) {
                throw new ValidacaoException("O código "+ listaDeCodigosResponsaveis.get(i).getCodigo() + " do responsável informado não existe");
            }
            var responsavel = responsavelRepository.findByCodigo(listaDeCodigosResponsaveis.get(i).getCodigo());
            listaCompletaDeResponsaveis.add(responsavel);
        }
        return listaCompletaDeResponsaveis;
    }
}
