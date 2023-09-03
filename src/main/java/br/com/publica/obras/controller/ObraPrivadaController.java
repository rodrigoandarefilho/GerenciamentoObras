package br.com.publica.obras.controller;

import br.com.publica.obras.domain.dto.ObraPrivadaDTO;
import br.com.publica.obras.domain.model.ObraPrivadaModel;
import br.com.publica.obras.domain.service.ObraPrivadaService;
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
@Tag(name = "Obra Privada")
@RequestMapping("obraprivada")
public class ObraPrivadaController {

    @Autowired
    private ObraPrivadaService obraPrivadaService;

    /**
     *@param obraPrivadaDTO Recebe um objeto do tipo Obra Privada
     *@param uriComponentsBuilder Recebe uma FactoryClass para obter instâncias de UriComponents, que são úteis para
     *construir URIs.
     *@return Retorna um ResponseEntity do tipo created e como tem a URI montada, realiza a busca pela obra privada
     * por ID e retorna o mesmo para o usuário
     */
    @PostMapping
    @Transactional
    @Operation(summary = "Realiza o cadastro da obra privada e retorna a mesma")
    public ResponseEntity<ObraPrivadaModel> cadastrarObraPrivada(@RequestBody @Valid ObraPrivadaDTO obraPrivadaDTO,
                                                                 UriComponentsBuilder uriComponentsBuilder) {
        var obraPrivada = obraPrivadaService.cadastrarObraPrivada(obraPrivadaDTO);
        var uri = uriComponentsBuilder.path("/obraprivada/{id}").buildAndExpand(obraPrivada.getId()).toUri();
        return ResponseEntity.created(uri).body(new ObraPrivadaModel(obraPrivada));
    }

    /**
     *@param id É o id da obra privada
     *@return Realiza a busca pela obra privada por id e retorna a mesma para o usuário.
     */
    @Hidden
    @GetMapping("/{id}")
    public ResponseEntity buscarObraPrivadaPorNumero(@PathVariable UUID id) {
        return ResponseEntity.ok(obraPrivadaService.buscarObraPrivadaPorId(id));
    }
}
