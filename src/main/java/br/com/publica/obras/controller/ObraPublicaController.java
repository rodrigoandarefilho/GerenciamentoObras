package br.com.publica.obras.controller;

import br.com.publica.obras.domain.Obra.ObraPublica.DadosCadastroObraPublica;
import br.com.publica.obras.domain.Obra.ObraPublica.DadosDetalhamentoObraPublica;
import br.com.publica.obras.domain.Obra.ObraPublica.ObraPublica;
import br.com.publica.obras.domain.responsavel.Responsavel;
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
        List<Responsavel> listaDeResponsaveis = new ArrayList<>();
        for (int i = 0; i < dadosCadastroObraPublica.dadosObra().responsaveis().size(); i++) {
            var responsavel = responsavelRepository.findResponsavelById(dadosCadastroObraPublica.dadosObra().responsaveis().get(i));
            listaDeResponsaveis.add(responsavel);
        }
        obraPublica.setResponsaveis(listaDeResponsaveis);
        obraPublicaRepository.save(obraPublica);
        var uri = uriComponentsBuilder.path("/obrapublica/{id}").buildAndExpand(obraPublica.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoObraPublica(obraPublica));
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarObraPrivadaPorID(@PathVariable BigDecimal id) {
        var obraPublica = obraPublicaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoObraPublica(obraPublica));
    }
}
