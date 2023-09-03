package br.com.publica.obras.controller;

import br.com.publica.obras.domain.dto.responsavel.ResponsavelDTO;
import br.com.publica.obras.domain.model.ResponsavelModel;
import br.com.publica.obras.domain.service.ResponsavelService;
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
@Tag(name = "Responsavel")
@RequestMapping("responsavel")
public class ResponsavelController {

    @Autowired
    private ResponsavelService responsavelService;

    /**
     *@param responsavelDTO Recebe um objeto do tipo responsavel
     *@param uriComponentsBuilder Recebe uma FactoryClass para obter instâncias de UriComponents, que são úteis para
     *construir URIs.
     *@return Retorna um ResponseEntity do tipo created e como tem a URI montada, realiza a busca pelo responsavel
     * por ID e retorna o mesmo para o usuário
     */
    @PostMapping
    @Transactional
    @Operation(summary = "Realiza o cadastro do responsável e retorna o mesmo")
    public ResponseEntity cadastrarResponsavel(@RequestBody @Valid ResponsavelDTO responsavelDTO, UriComponentsBuilder uriComponentsBuilder) {
        var responsavel = responsavelService.cadastrarResponsavel(responsavelDTO);
        var uri = uriComponentsBuilder.path("/responsavel/{id}").buildAndExpand(responsavel.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponsavelModel(responsavel));
    }

    /**
     *@param id Recebe um id do responsavel
     *@return Retorna um ResponseEntity do tipo ok e envia o usuário encontrado no corpo do retorno da requisição
     */
    @Hidden
    @GetMapping("/{id}")
    public ResponseEntity buscarResponsavelPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(responsavelService.buscarResponsavelPorId(id));
    }
}
