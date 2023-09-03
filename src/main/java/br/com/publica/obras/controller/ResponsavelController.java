package br.com.publica.obras.controller;

import br.com.publica.obras.domain.dto.DadosCadastroResponsavel;
import br.com.publica.obras.domain.model.DadosDetalhamentoResponsavel;
import br.com.publica.obras.domain.service.ResponsavelService;
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

@Tag(name = "Responsavel")
@RestController
@RequestMapping("responsavel")
public class ResponsavelController {

    @Autowired
    private ResponsavelService responsavelService;

    @PostMapping
    @Transactional
    @Operation(summary = "Realiza o cadastro do responsável e retorna o mesmo")
    public ResponseEntity cadastrarResponsavel(@RequestBody @Valid DadosCadastroResponsavel dadosCadastroResponsavel, UriComponentsBuilder uriComponentsBuilder) {
        var responsavel = responsavelService.cadastrarResponsavel(dadosCadastroResponsavel);
        var uri = uriComponentsBuilder.path("/responsavel/{id}").buildAndExpand(responsavel.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoResponsavel(responsavel));
    }

    @Hidden
    @GetMapping("/{id}")
    public ResponseEntity buscarResponsavelPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(responsavelService.buscarResponsavelPorId(id));
    }
}
