package br.com.publica.obras.domain.service;

import br.com.publica.obras.domain.model.DadosDetalhamentoObra;
import br.com.publica.obras.domain.model.DadosDetalhamentoObraPrivada;
import br.com.publica.obras.domain.model.DadosDetalhamentoObraPublica;
import br.com.publica.obras.repository.ObraPrivadaRepository;
import br.com.publica.obras.repository.ObraPublicaRepository;
import br.com.publica.obras.repository.ObraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ObraService {

    @Autowired
    private ObraRepository obraRepository;
    @Autowired
    private ObraPublicaRepository obraPublicaRepository;
    @Autowired
    private ObraPrivadaRepository obraPrivadaRepository;

    public List<DadosDetalhamentoObra> buscarTodasObras(Pageable paginacao) {
        return obraRepository.findAll(paginacao).stream().map(DadosDetalhamentoObra::new).toList();
    }

    public List<DadosDetalhamentoObraPublica> buscarTodasObrasPublicas(Pageable paginacao) {
        return obraPublicaRepository.findAll(paginacao).stream().map(DadosDetalhamentoObraPublica::new).toList();
    }

    public List<DadosDetalhamentoObraPrivada> buscarTodasObrasPrivadas(Pageable paginacao) {
        return obraPrivadaRepository.findAll(paginacao).stream().map(DadosDetalhamentoObraPrivada::new).toList();
    }

    public List<DadosDetalhamentoObra> buscarObrasPorResponsavel(BigDecimal codigoDoResponsavel) {
        return obraRepository.findAllObrasPorResponsavel(codigoDoResponsavel).stream().map(DadosDetalhamentoObra::new).toList();
    }
}
