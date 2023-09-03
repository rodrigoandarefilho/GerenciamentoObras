package br.com.publica.obras.domain.service;

import br.com.publica.obras.domain.dto.DadosCadastroObraPublica;
import br.com.publica.obras.domain.entity.ObraPublica;
import br.com.publica.obras.domain.model.DadosDetalhamentoObraPublica;
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

    public ObraPublica cadastrarObraPublica(DadosCadastroObraPublica dadosCadastroObraPublica) {
        var obraPublica = new ObraPublica(dadosCadastroObraPublica);
        var listaDeCodigosResponsaveis = dadosCadastroObraPublica.dadosObra().responsaveis();
        obraPublica.setResponsaveis(responsavelService.gerarListaDeResponsaveis(listaDeCodigosResponsaveis));
        return obraPublicaRepository.save(obraPublica);
    }

    public DadosDetalhamentoObraPublica buscarObraPrivadaPorID(UUID id) {
        return new DadosDetalhamentoObraPublica(obraPublicaRepository.getReferenceById(id));
    }
}
