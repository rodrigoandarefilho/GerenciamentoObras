package br.com.publica.obras.controller;

import br.com.publica.obras.domain.responsavel.DadosCadastroResponsavel;
import br.com.publica.obras.domain.responsavel.DadosDetalhamentoResponsavel;
import br.com.publica.obras.domain.responsavel.Responsavel;
import br.com.publica.obras.infra.exception.ValidacaoException;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Tag(name = "Responsavel")
@RestController
@RequestMapping("responsavel")
public class ResponsavelController {

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @PostMapping
    @Transactional
    @Operation(summary = "Realiza o cadastro do responsável e retorna o mesmo")
    public ResponseEntity cadastrarResponsavel(@RequestBody @Valid DadosCadastroResponsavel dadosCadastroResponsavel, UriComponentsBuilder uriComponentsBuilder) {
        var responsavel = new Responsavel(dadosCadastroResponsavel);
        responsavelRepository.save(responsavel);
        var uri = uriComponentsBuilder.path("/responsavel/{id}").buildAndExpand(responsavel.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoResponsavel(responsavel));
    }

    @Hidden
    @GetMapping("/{id}")
    public ResponseEntity buscarResponsavelPorId(@PathVariable UUID id) {
        var responsavel = responsavelRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoResponsavel(responsavel));
    }

    public List<Responsavel> gerarListaCompletaDeResponsaveis(List<Responsavel> listaDeCodigosResponsaveis) {
        List<Responsavel> listaCompletaDeResponsaveis = new ArrayList<>();
        for (int i = 0; i < listaDeCodigosResponsaveis.size(); i++) {
            if (!responsavelRepository.existsByCodigo(listaDeCodigosResponsaveis.get(i).getCodigo())) {
                throw new ValidacaoException("O código "+ listaDeCodigosResponsaveis.get(i).getCodigo() + " do responsável informado não existe");
            }
            var responsavel = responsavelRepository.findByCodigo(listaDeCodigosResponsaveis.get(i).getCodigo());
            listaCompletaDeResponsaveis.add(responsavel);
        }
        return listaCompletaDeResponsaveis;
    }
}
