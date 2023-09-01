package br.com.publica.obras.controller;

import br.com.publica.obras.domain.responsavel.DadosCadastroResponsavel;
import br.com.publica.obras.domain.responsavel.DadosDetalhamentoResponsavel;
import br.com.publica.obras.domain.responsavel.Responsavel;
import br.com.publica.obras.infra.exception.ValidacaoException;
import br.com.publica.obras.repository.ResponsavelRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;

@RestController
@RequestMapping("responsavel")
public class ResponsavelController {

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarResponsavel(@RequestBody @Valid DadosCadastroResponsavel dadosCadastroResponsavel, UriComponentsBuilder uriComponentsBuilder) {
        var responsavel = new Responsavel(dadosCadastroResponsavel);
        responsavelRepository.save(responsavel);
        var uri = uriComponentsBuilder.path("/responsavel/{codigo}").buildAndExpand(responsavel.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoResponsavel(responsavel));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity buscarResponsavelPorId(@PathVariable BigDecimal codigo) {
        var responsavel = responsavelRepository.findByCodigo(codigo);
        if (!responsavelRepository.existsByCodigo(codigo)) {
            throw new ValidacaoException("O código "+ codigo + " do responsável informado não existe");
        }
        return ResponseEntity.ok(new DadosDetalhamentoResponsavel(responsavel));
    }
}
