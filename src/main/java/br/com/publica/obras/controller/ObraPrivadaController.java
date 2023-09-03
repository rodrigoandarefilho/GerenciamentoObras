package br.com.publica.obras.controller;

import br.com.publica.obras.domain.dto.DadosCadastroObraPrivada;
import br.com.publica.obras.domain.model.DadosDetalhamentoObraPrivada;
import br.com.publica.obras.domain.entity.ObraPrivada;
import br.com.publica.obras.domain.service.ResponsavelService;
import br.com.publica.obras.repository.ObraPrivadaRepository;
import br.com.publica.obras.repository.ResponsavelRepository;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@Tag(name = "Obra Privada")
@RequestMapping("obraprivada")
public class ObraPrivadaController {

    @Autowired
    private ObraPrivadaRepository obraPrivadaRepository;
    @Autowired
    private ResponsavelRepository responsavelRepository;
    @Autowired
    private ResponsavelService responsavelService;

    @PostMapping
    @Transactional
    @Operation(summary = "Realiza o cadastro da obra privada e retorna a mesma")
    public ResponseEntity<DadosDetalhamentoObraPrivada> cadastrarObraPrivada(@RequestBody @Valid DadosCadastroObraPrivada dadosCadastroObraPrivada,
                                                                             UriComponentsBuilder uriComponentsBuilder) {
        var obraPrivada = new ObraPrivada(dadosCadastroObraPrivada);
        var listaDeCodigosResponsaveis = dadosCadastroObraPrivada.dadosObra().responsaveis();
        obraPrivada.setResponsaveis(responsavelService.gerarListaDeResponsaveis(listaDeCodigosResponsaveis));
        obraPrivadaRepository.save(obraPrivada);
        var uri = uriComponentsBuilder.path("/obraprivada/{id}").buildAndExpand(obraPrivada.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoObraPrivada(obraPrivada));
    }

    @Hidden
    @GetMapping("/{id}")
    public ResponseEntity buscarObraPrivadaPorNumero(@PathVariable UUID id) {
        var obraPrivada = obraPrivadaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoObraPrivada(obraPrivada));
    }
}
