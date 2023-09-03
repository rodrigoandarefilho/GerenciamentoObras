package br.com.publica.obras.controller;

import br.com.publica.obras.domain.obraPrivada.DadosCadastroObraPrivada;
import br.com.publica.obras.domain.obraPrivada.DadosDetalhamentoObraPrivada;
import br.com.publica.obras.domain.obraPrivada.ObraPrivada;
import br.com.publica.obras.repository.ObraPrivadaRepository;
import br.com.publica.obras.repository.ResponsavelRepository;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@Tag(name = "Cadastrar Obras Públicas")
@RequestMapping("obraprivada")
public class ObraPrivadaController {

    @Autowired
    private ObraPrivadaRepository obraPrivadaRepository;
    @Autowired
    private ResponsavelRepository responsavelRepository;
    @Autowired
    private ResponsavelController responsavelController;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoObraPrivada> cadastrarObraPrivada(@RequestBody @Valid DadosCadastroObraPrivada dadosCadastroObraPrivada,
                                                                             UriComponentsBuilder uriComponentsBuilder) {
        var obraPrivada = new ObraPrivada(dadosCadastroObraPrivada);
        var listaDeCodigosResponsaveis = dadosCadastroObraPrivada.dadosObra().responsaveis();
        obraPrivada.setResponsaveis(responsavelController.gerarListaCompletaDeResponsaveis(listaDeCodigosResponsaveis));
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
