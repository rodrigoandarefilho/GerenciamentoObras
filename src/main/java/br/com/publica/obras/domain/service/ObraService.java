package br.com.publica.obras.domain.service;

import br.com.publica.obras.domain.model.ObraModel;
import br.com.publica.obras.domain.model.ObraPrivadaModel;
import br.com.publica.obras.domain.model.ObraPublicaModel;
import br.com.publica.obras.infra.exception.ValidacaoException;
import br.com.publica.obras.repository.ObraPrivadaRepository;
import br.com.publica.obras.repository.ObraPublicaRepository;
import br.com.publica.obras.repository.ObraRepository;
import br.com.publica.obras.repository.ResponsavelRepository;
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
    @Autowired
    private ResponsavelRepository responsavelRepository;

    public List<ObraModel> buscarTodasObras(Pageable paginacao) {
        return obraRepository.findAll(paginacao).stream().map(ObraModel::new).toList();
    }

    public List<ObraPublicaModel> buscarTodasObrasPublicas(Pageable paginacao) {
        return obraPublicaRepository.findAll(paginacao).stream().map(ObraPublicaModel::new).toList();
    }

    public List<ObraPrivadaModel> buscarTodasObrasPrivadas(Pageable paginacao) {
        return obraPrivadaRepository.findAll(paginacao).stream().map(ObraPrivadaModel::new).toList();
    }

    public List<ObraModel> buscarObrasPorResponsavel(BigDecimal codigoDoResponsavel) {
        if(!responsavelRepository.existsByCodigo(codigoDoResponsavel)) {
            throw new ValidacaoException("Não tem nenhum responsável cadastrado com o código " + codigoDoResponsavel);
        }
        return obraRepository.findAllObrasPorResponsavel(codigoDoResponsavel).stream().map(ObraModel::new).toList();
    }
}