package br.com.publica.obras.controller;

import br.com.publica.obras.domain.dto.DadosCadastroObraPublica;
import br.com.publica.obras.domain.model.DadosDetalhamentoObraPublica;
import br.com.publica.obras.domain.entity.ObraPublica;
import br.com.publica.obras.domain.service.ObraPublicaService;
import br.com.publica.obras.domain.service.ResponsavelService;
import br.com.publica.obras.repository.ObraPublicaRepository;
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
@RequestMapping("obrapublica")
@Tag(name = "Obra Pública")
public class ObraPublicaController {
    @Autowired
    private ObraPublicaService obraPublicaService;

    @PostMapping
    @Transactional
    @Operation(summary = "Realiza o cadastro da obra pública e retorna a mesma")
    public ResponseEntity<DadosDetalhamentoObraPublica> cadastrarObraPublica(@RequestBody @Valid DadosCadastroObraPublica dadosCadastroObraPublica,
                                                                             UriComponentsBuilder uriComponentsBuilder) {
        var obraPublica = obraPublicaService.cadastrarObraPublica(dadosCadastroObraPublica);
        var uri = uriComponentsBuilder.path("/obrapublica/{id}").buildAndExpand(obraPublica.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoObraPublica(obraPublica));
    }

    @Hidden
    @GetMapping("/{id}")
    public ResponseEntity buscarObraPrivadaPorID(@PathVariable UUID id) {
        return ResponseEntity.ok(obraPublicaService.buscarObraPrivadaPorID(id));
    }
}
