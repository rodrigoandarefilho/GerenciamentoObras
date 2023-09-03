package br.com.publica.obras.controller;

import br.com.publica.obras.domain.dto.DadosCadastroObraPrivada;
import br.com.publica.obras.domain.model.DadosDetalhamentoObraPrivada;
import br.com.publica.obras.domain.service.ObraPrivadaService;
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
    private ObraPrivadaService obraPrivadaService;

    @PostMapping
    @Transactional
    @Operation(summary = "Realiza o cadastro da obra privada e retorna a mesma")
    public ResponseEntity<DadosDetalhamentoObraPrivada> cadastrarObraPrivada(@RequestBody @Valid DadosCadastroObraPrivada dadosCadastroObraPrivada,
                                                                             UriComponentsBuilder uriComponentsBuilder) {
        var obraPrivada = obraPrivadaService.cadastrarObraPrivada(dadosCadastroObraPrivada);
        var uri = uriComponentsBuilder.path("/obraprivada/{id}").buildAndExpand(obraPrivada.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoObraPrivada(obraPrivada));
    }

    @Hidden
    @GetMapping("/{id}")
    public ResponseEntity buscarObraPrivadaPorNumero(@PathVariable UUID id) {
        return ResponseEntity.ok(obraPrivadaService.buscarObraPrivadaPorNumero(id));
    }
}
