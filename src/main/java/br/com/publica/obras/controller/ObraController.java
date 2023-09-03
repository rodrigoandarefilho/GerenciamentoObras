package br.com.publica.obras.controller;

import br.com.publica.obras.domain.model.DadosDetalhamentoObra;
import br.com.publica.obras.domain.model.DadosDetalhamentoObraPrivada;
import br.com.publica.obras.domain.model.DadosDetalhamentoObraPublica;
import br.com.publica.obras.domain.service.ObraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("obras")
@Tag(name = "Obras")
public class ObraController {

    @Autowired
    private ObraService obraService;

    @GetMapping
    @Operation(summary = "Realiza a consulta de todas as obras cadastradas")
    public ResponseEntity<List<DadosDetalhamentoObra>> buscarTodasObras(@PageableDefault(size = 10, sort = {"numero"}, direction = Sort.Direction.ASC) Pageable paginacao) {
        return ResponseEntity.ok(obraService.buscarTodasObras(paginacao));
    }

    @GetMapping("/publica")
    @Operation(summary = "Realiza a consulta de obras públicas")
    public ResponseEntity<List<DadosDetalhamentoObraPublica>> buscarTodasObrasPublicas(@PageableDefault(size = 10) Pageable paginacao) {
        return ResponseEntity.ok(obraService.buscarTodasObrasPublicas(paginacao));
    }

    @GetMapping("/privada")
    @Operation(summary = "Realiza a consulta de obras privadas")
    public ResponseEntity<List<DadosDetalhamentoObraPrivada>> buscarTodasObrasPrivadas(@PageableDefault(size = 10) Pageable paginacao) {
        return ResponseEntity.ok(obraService.buscarTodasObrasPrivadas(paginacao));
    }

    @GetMapping("/{codigoDoResponsavel}")
    @Operation(summary = "Realiza a consulta de obras utilizando o código do responsável como chave para pesquisa")
    public ResponseEntity<List<DadosDetalhamentoObra>> buscarObrasPorResponsavel(@PathVariable BigDecimal codigoDoResponsavel) {
        return ResponseEntity.ok(obraService.buscarObrasPorResponsavel(codigoDoResponsavel));
    }
}
