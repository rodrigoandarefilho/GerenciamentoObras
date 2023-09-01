package br.com.publica.obras.controller;

import br.com.publica.obras.domain.obra.DadosDetalhamentoObra;
import br.com.publica.obras.domain.obra.obraPrivada.DadosDetalhamentoObraPrivada;
import br.com.publica.obras.domain.obra.obraPublica.DadosDetalhamentoObraPublica;
import br.com.publica.obras.repository.ObraPrivadaRepository;
import br.com.publica.obras.repository.ObraPublicaRepository;
import br.com.publica.obras.repository.ObraRepository;
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
public class ObraController {

    @Autowired
    private ObraPublicaRepository obraPublicaRepository;

    @Autowired
    private ObraPrivadaRepository obraPrivadaRepository;

    @Autowired
    private ObraRepository obraRepository;

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoObra>> buscarTodasObras(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.ASC) Pageable paginacao) {
        var obras = obraRepository.findAll(paginacao).stream().map(DadosDetalhamentoObra::new).toList();
        return ResponseEntity.ok(obras);
    }

    @GetMapping("/publica")
    public ResponseEntity<List<DadosDetalhamentoObraPublica>> buscarTodasObrasPublicas(@PageableDefault(size = 10) Pageable paginacao) {
        var obrasPublicas = obraPublicaRepository.findAll(paginacao).stream().map(DadosDetalhamentoObraPublica::new).toList();
        return ResponseEntity.ok(obrasPublicas);
    }

    @GetMapping("/privada")
    public ResponseEntity<List<DadosDetalhamentoObraPrivada>> buscarTodasObrasPrivadas(@PageableDefault(size = 10) Pageable paginacao) {
        var obrasPrivadas = obraPrivadaRepository.findAll(paginacao).stream().map(DadosDetalhamentoObraPrivada::new).toList();
        return ResponseEntity.ok(obrasPrivadas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<DadosDetalhamentoObra>> buscarObrasPorResponsavel(@PathVariable BigDecimal id) {
        var obrasPorResponsavel = obraRepository.findAllObrasPorResponsavel(id).stream().map(DadosDetalhamentoObra::new).toList();
        return ResponseEntity.ok(obrasPorResponsavel);
    }
}
