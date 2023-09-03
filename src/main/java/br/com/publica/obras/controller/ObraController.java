package br.com.publica.obras.controller;

import br.com.publica.obras.domain.model.DadosDetalhamentoObra;
import br.com.publica.obras.domain.model.DadosDetalhamentoObraPrivada;
import br.com.publica.obras.domain.model.DadosDetalhamentoObraPublica;
import br.com.publica.obras.repository.ObraPrivadaRepository;
import br.com.publica.obras.repository.ObraPublicaRepository;
import br.com.publica.obras.repository.ObraRepository;
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
    private ObraPublicaRepository obraPublicaRepository;

    @Autowired
    private ObraPrivadaRepository obraPrivadaRepository;

    @Autowired
    private ObraRepository obraRepository;

    @GetMapping
    @Operation(summary = "Realiza a consulta de todas as obras cadastradas")
    public ResponseEntity<List<DadosDetalhamentoObra>> buscarTodasObras(@PageableDefault(size = 10, sort = {"numero"}, direction = Sort.Direction.ASC) Pageable paginacao) {
        var obras = obraRepository.findAll(paginacao).stream().map(DadosDetalhamentoObra::new).toList();
        return ResponseEntity.ok(obras);
    }

    @GetMapping("/publica")
    @Operation(summary = "Realiza a consulta de obras públicas")
    public ResponseEntity<List<DadosDetalhamentoObraPublica>> buscarTodasObrasPublicas(@PageableDefault(size = 10) Pageable paginacao) {
        var obrasPublicas = obraPublicaRepository.findAll(paginacao).stream().map(DadosDetalhamentoObraPublica::new).toList();
        return ResponseEntity.ok(obrasPublicas);
    }

    @GetMapping("/privada")
    @Operation(summary = "Realiza a consulta de obras privadas")
    public ResponseEntity<List<DadosDetalhamentoObraPrivada>> buscarTodasObrasPrivadas(@PageableDefault(size = 10) Pageable paginacao) {
        var obrasPrivadas = obraPrivadaRepository.findAll(paginacao).stream().map(DadosDetalhamentoObraPrivada::new).toList();
        return ResponseEntity.ok(obrasPrivadas);
    }

    @GetMapping("/{codigoDoResponsavel}")
    @Operation(summary = "Realiza a consulta de obras utilizando o código do responsável como chave para pesquisa")
    public ResponseEntity<List<DadosDetalhamentoObra>> buscarObrasPorResponsavel(@PathVariable BigDecimal codigoDoResponsavel) {
        var obrasPorResponsavel = obraRepository.findAllObrasPorResponsavel(codigoDoResponsavel).stream().map(DadosDetalhamentoObra::new).toList();
        return ResponseEntity.ok(obrasPorResponsavel);
    }
}
