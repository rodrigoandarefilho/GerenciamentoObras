package br.com.publica.obras.controller;

import br.com.publica.obras.domain.Obra.ObraPrivada.DadosCadastroObraPrivada;
import br.com.publica.obras.domain.Obra.ObraPrivada.DadosDetalhamentoObraPrivada;
import br.com.publica.obras.domain.Obra.ObraPrivada.ObraPrivada;
import br.com.publica.obras.domain.responsavel.Responsavel;
import br.com.publica.obras.infra.exception.ValidacaoException;
import br.com.publica.obras.repository.ObraPrivadaRepository;
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
@RequestMapping("obraprivada")
public class ObraPrivadaController {

    @Autowired
    private ObraPrivadaRepository obraPrivadaRepository;
    @Autowired
    private ResponsavelRepository responsavelRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoObraPrivada> cadastrarObraPrivada(@RequestBody @Valid DadosCadastroObraPrivada dadosCadastroObraPrivada,
                                                                             UriComponentsBuilder uriComponentsBuilder) {
        var obraPrivada = new ObraPrivada(dadosCadastroObraPrivada);
        System.out.println(validacaoResponsaveis(dadosCadastroObraPrivada, obraPrivada));
        obraPrivadaRepository.save(validacaoResponsaveis(dadosCadastroObraPrivada, obraPrivada));
        var uri = uriComponentsBuilder.path("/obraprivada/{id}").buildAndExpand(obraPrivada.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoObraPrivada(obraPrivada));
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarObraPrivadaPorID(@PathVariable BigDecimal id) {
        var obraPrivada = obraPrivadaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoObraPrivada(obraPrivada));
    }

    private ObraPrivada validacaoResponsaveis(DadosCadastroObraPrivada dadosCadastroObraPrivada, ObraPrivada obraPrivada) {
        List<Responsavel> listaDeResponsaveis = new ArrayList<>();
        for (int i = 0; i < dadosCadastroObraPrivada.dadosObra().responsaveis().size(); i++) {
            if (!responsavelRepository.existsById(dadosCadastroObraPrivada.dadosObra().responsaveis().get(i).getId())) {
                throw new ValidacaoException("Id "+ dadosCadastroObraPrivada.dadosObra().responsaveis().get(i) + " do responsável informado não existe");
            }
            var responsavel = responsavelRepository.findResponsavelById(dadosCadastroObraPrivada.dadosObra().responsaveis().get(i).getId());
            listaDeResponsaveis.add(responsavel);
        }
        obraPrivada.setResponsaveis(listaDeResponsaveis);
        return obraPrivada;
    }
}
