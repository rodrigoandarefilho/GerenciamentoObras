package br.com.publica.obras.controller;

import br.com.publica.obras.domain.obra.obraPublica.DadosCadastroObraPublica;
import br.com.publica.obras.domain.obra.obraPublica.DadosDetalhamentoObraPublica;
import br.com.publica.obras.domain.obra.obraPublica.ObraPublica;
import br.com.publica.obras.repository.ObraPublicaRepository;
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
@RequestMapping("obrapublica")
@Tag(name = "Cadastrar Obras Privadas")
public class ObraPublicaController {

    @Autowired
    private ObraPublicaRepository obraPublicaRepository;
    @Autowired
    private ResponsavelRepository responsavelRepository;
    @Autowired
    private ResponsavelController responsavelController;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoObraPublica> cadastrarObraPublica(@RequestBody @Valid DadosCadastroObraPublica dadosCadastroObraPublica,
                                                                             UriComponentsBuilder uriComponentsBuilder) {
        var obraPublica = new ObraPublica(dadosCadastroObraPublica);
        var listaDeCodigosResponsaveis = dadosCadastroObraPublica.dadosObra().responsaveis();
        obraPublica.setResponsaveis(responsavelController.gerarListaCompletaDeResponsaveis(listaDeCodigosResponsaveis));
        obraPublicaRepository.save(obraPublica);
        var uri = uriComponentsBuilder.path("/obrapublica/{id}").buildAndExpand(obraPublica.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoObraPublica(obraPublica));
    }

    @Hidden
    @GetMapping("/{id}")
    public ResponseEntity buscarObraPrivadaPorID(@PathVariable UUID id) {
        var obraPublica = obraPublicaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoObraPublica(obraPublica));
    }
}
