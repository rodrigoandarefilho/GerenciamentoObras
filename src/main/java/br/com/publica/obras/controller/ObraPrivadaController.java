package br.com.publica.obras.controller;

import br.com.publica.obras.domain.Obra.ObraPrivada.DadosCadastroObraPrivada;
import br.com.publica.obras.domain.Obra.ObraPrivada.DadosDetalhamentoObraPrivada;
import br.com.publica.obras.domain.Obra.ObraPrivada.ObraPrivada;
import br.com.publica.obras.domain.responsavel.Responsavel;
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
        List<Responsavel> listaDeResponsaveis = new ArrayList<>();
        for (int i = 0; i < dadosCadastroObraPrivada.dadosObra().responsaveis().size(); i++) {
            var responsavel = responsavelRepository.findResponsavelById(dadosCadastroObraPrivada.dadosObra().responsaveis().get(i));
            listaDeResponsaveis.add(responsavel);
        }
        obraPrivada.setResponsaveis(listaDeResponsaveis);
        obraPrivadaRepository.save(obraPrivada);
        var uri = uriComponentsBuilder.path("/obraprivada/{id}").buildAndExpand(obraPrivada.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoObraPrivada(obraPrivada));
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarObraPrivadaPorID(@PathVariable BigDecimal id) {
        var obraPrivada = obraPrivadaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoObraPrivada(obraPrivada));
    }
}
