package br.com.publica.obras.controller;

import br.com.publica.obras.domain.dto.ObraPublicaDTO;
import br.com.publica.obras.domain.model.ObraPublicaModel;
import br.com.publica.obras.domain.service.ObraPublicaService;
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

    /**
     *@param obraPublicaDTO Recebe um objeto do tipo Obra Publica
     *@param uriComponentsBuilder Recebe uma FactoryClass para obter instâncias de UriComponents, que são úteis para
     *construir URIs.
     *@return Retorna um ResponseEntity do tipo created e como tem a URI montada, realiza a busca pela obra publica
     * por ID e retorna o mesmo para o usuário
     */
    @PostMapping
    @Transactional
    @Operation(summary = "Realiza o cadastro da obra pública e retorna a mesma")
    public ResponseEntity cadastrarObraPublica(@RequestBody @Valid ObraPublicaDTO obraPublicaDTO,
                                                                 UriComponentsBuilder uriComponentsBuilder) {
        var obraPublica = obraPublicaService.cadastrarObraPublica(obraPublicaDTO);
        var uri = uriComponentsBuilder.path("/obrapublica/{id}").buildAndExpand(obraPublica.getId()).toUri();
        return ResponseEntity.created(uri).body(new ObraPublicaModel(obraPublica));
    }

    /**
     *@param id É o id da obra publica
     *@return Realiza a busca pela obra publica por id e retorna a mesma para o usuário.
     */
    @Hidden
    @GetMapping("/{id}")
    public ResponseEntity buscarObraPrivadaPorID(@PathVariable UUID id) {
        return ResponseEntity.ok(obraPublicaService.buscarObraPublicaPorID(id));
    }
}
