package br.com.publica.obras.controller;

import br.com.publica.obras.domain.Obra.DadosDetalhamentoObra;
import br.com.publica.obras.domain.Obra.ObraPrivada.DadosDetalhamentoObraPrivada;
import br.com.publica.obras.domain.Obra.ObraPublica.DadosDetalhamentoObraPublica;
import br.com.publica.obras.repository.ObraPrivadaRepository;
import br.com.publica.obras.repository.ObraPublicaRepository;
import br.com.publica.obras.repository.ObraRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<DadosDetalhamentoObra>> buscarTodasObras() {
        var obras = obraRepository.findAll().stream().map(DadosDetalhamentoObra::new).toList();
        return ResponseEntity.ok(obras);
    }

    @GetMapping("/publica")
    public ResponseEntity<List<DadosDetalhamentoObraPublica>> buscarTodasObrasPublicas() {
        var obrasPublicas = obraPublicaRepository.findAll().stream().map(DadosDetalhamentoObraPublica::new).toList();
        return ResponseEntity.ok(obrasPublicas);
    }

    @GetMapping("/privada")
    public ResponseEntity<List<DadosDetalhamentoObraPrivada>> buscarTodasObrasPrivadas() {
        var obrasPrivadas = obraPrivadaRepository.findAll().stream().map(DadosDetalhamentoObraPrivada::new).toList();
        return ResponseEntity.ok(obrasPrivadas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<DadosDetalhamentoObra>> buscarObrasPorResponsavel(@PathVariable BigDecimal id) {
        var obrasPorResponsavel = obraRepository.findAllObrasPorResponsavel(id).stream().map(DadosDetalhamentoObra::new).toList();
        return ResponseEntity.ok(obrasPorResponsavel);
    }
}
