package br.com.publica.obras.domain.service;

import br.com.publica.obras.domain.dto.DadosCadastroObraPrivada;
import br.com.publica.obras.domain.entity.ObraPrivada;
import br.com.publica.obras.domain.model.DadosDetalhamentoObraPrivada;
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

    public ObraPrivada cadastrarObraPrivada(DadosCadastroObraPrivada dadosCadastroObraPrivada) {
        var obraPrivada = new ObraPrivada(dadosCadastroObraPrivada);

        var listaDeCodigosResponsaveis = dadosCadastroObraPrivada.dadosObra().responsaveis();
        obraPrivada.setResponsaveis(responsavelService.gerarListaDeResponsaveis(listaDeCodigosResponsaveis));
        return obraPrivadaRepository.save(obraPrivada);
    }
    public DadosDetalhamentoObraPrivada buscarObraPrivadaPorNumero(UUID id) {
        return new DadosDetalhamentoObraPrivada(obraPrivadaRepository.getReferenceById(id));
    }
}
