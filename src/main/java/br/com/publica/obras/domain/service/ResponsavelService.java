package br.com.publica.obras.domain.service;

import br.com.publica.obras.domain.dto.DadosCadastroResponsavel;
import br.com.publica.obras.domain.entity.Responsavel;
import br.com.publica.obras.domain.model.DadosDetalhamentoResponsavel;
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

    public Responsavel cadastrarResponsavel(DadosCadastroResponsavel dadosCadastroResponsavel) {
        var responsavel = new Responsavel(dadosCadastroResponsavel);
        return responsavelRepository.save(responsavel);
    }

    public DadosDetalhamentoResponsavel buscarResponsavelPorId(UUID id) {
        return new DadosDetalhamentoResponsavel(responsavelRepository.getReferenceById(id));
    }

    public List<Responsavel> gerarListaDeResponsaveis(List<Responsavel> listaDeCodigosResponsaveis) {
        List<Responsavel> listaCompletaDeResponsaveis = new ArrayList<>();
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
