package br.com.publica.obras.controller;

import br.com.publica.obras.domain.obra.obraPublica.DadosCadastroObraPublica;
import br.com.publica.obras.domain.obra.obraPublica.DadosDetalhamentoObraPublica;
import br.com.publica.obras.domain.obra.obraPublica.ObraPublica;
import br.com.publica.obras.domain.responsavel.Responsavel;
import br.com.publica.obras.infra.exception.ValidacaoException;
import br.com.publica.obras.repository.ObraPublicaRepository;
import br.com.publica.obras.repository.ResponsavelRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("obrapublica")
public class ObraPublicaController {

    @Autowired
    private ObraPublicaRepository obraPublicaRepository;
    @Autowired
    private ResponsavelRepository responsavelRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoObraPublica> cadastrarObraPublica(@RequestBody @Valid DadosCadastroObraPublica dadosCadastroObraPublica,
                                                                             UriComponentsBuilder uriComponentsBuilder) {
        var obraPublica = new ObraPublica(dadosCadastroObraPublica);
        obraPublicaRepository.save(validacaoResponsaveis(dadosCadastroObraPublica, obraPublica));
        var uri = uriComponentsBuilder.path("/obrapublica/{id}").buildAndExpand(obraPublica.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoObraPublica(obraPublica));
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarObraPrivadaPorID(@PathVariable BigDecimal id) {
        var obraPublica = obraPublicaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoObraPublica(obraPublica));
    }

    private ObraPublica validacaoResponsaveis(DadosCadastroObraPublica dadosCadastroObraPublica, ObraPublica obraPublica) {
        List<Responsavel> listaDeResponsaveis = new ArrayList<>();
        for (int i = 0; i < dadosCadastroObraPublica.dadosObra().responsaveis().size(); i++) {
            if (!responsavelRepository.existsById(dadosCadastroObraPublica.dadosObra().responsaveis().get(i).getId())) {
                throw new ValidacaoException("Id "+ dadosCadastroObraPublica.dadosObra().responsaveis().get(i) + " do responsável informado não existe");
            }
            var responsavel = responsavelRepository.findResponsavelById(dadosCadastroObraPublica.dadosObra().responsaveis().get(i).getId());
            listaDeResponsaveis.add(responsavel);
        }
        obraPublica.setResponsaveis(listaDeResponsaveis);
        return obraPublica;
    }
}
