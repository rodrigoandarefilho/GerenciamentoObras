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

    /**
     *@param paginacao Recebe um atributo do tipo Pageable que costuma ser útil
     *quando temos um conjunto de dados grande e queremos apresentá-lo ao usuário em partes menores.
     *@return Retorna uma lista com todas as obras cadastradas
     */
    public List<ObraModel> buscarTodasObras(Pageable paginacao) {
        return obraRepository.findAll(paginacao).stream().map(ObraModel::new).toList();
    }

    /**
     *@param paginacao Recebe um atributo do tipo Pageable que costuma ser útil
     *quando temos um conjunto de dados grande e queremos apresentá-lo ao usuário em partes menores.
     *@return Retorna uma lista com todas as obras publicas cadastradas
     */
    public List<ObraPublicaModel> buscarTodasObrasPublicas(Pageable paginacao) {
        return obraPublicaRepository.findAll(paginacao).stream().map(ObraPublicaModel::new).toList();
    }

    /**
     *@param paginacao Recebe um atributo do tipo Pageable que costuma ser útil
     *quando temos um conjunto de dados grande e queremos apresentá-lo ao usuário em partes menores.
     *@return Retorna uma lista com todas as obras prrivadas cadastradas
     */
    public List<ObraPrivadaModel> buscarTodasObrasPrivadas(Pageable paginacao) {
        return obraPrivadaRepository.findAll(paginacao).stream().map(ObraPrivadaModel::new).toList();
    }

    /**
     *@param codigoDoResponsavel Recebe um responsável
     *@return Verifica se existe no bando o codigo do responsável e
     *retorna uma lista de Obras referente ao codigo do responsavel
     */
    public List<ObraModel> buscarObrasPorResponsavel(BigDecimal codigoDoResponsavel) {
        if(!responsavelRepository.existsByCodigo(codigoDoResponsavel)) {
            throw new ValidacaoException("Não tem nenhum responsável cadastrado com o código " + codigoDoResponsavel);
        }
        return obraRepository.findAllObrasPorResponsavel(codigoDoResponsavel).stream().map(ObraModel::new).toList();
    }
}