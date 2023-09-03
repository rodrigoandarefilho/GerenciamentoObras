package br.com.publica.obras.controller;

import br.com.publica.obras.domain.model.ObraModel;
import br.com.publica.obras.domain.model.ObraPrivadaModel;
import br.com.publica.obras.domain.model.ObraPublicaModel;
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

    /**
     *@param paginacao parâmetro que define a pginação
     *@return Retorna um Page, que defina os parâmetros de paginação, verifica no banco de dados e então
     * retornar um Pageobjeto do tipo Obra ao cliente.
     */
    @GetMapping
    @Operation(summary = "Realiza a consulta de todas as obras cadastradas")
    public ResponseEntity<List<ObraModel>> buscarTodasObras(@PageableDefault(size = 10, sort = {"numero"}, direction = Sort.Direction.ASC) Pageable paginacao) {
        return ResponseEntity.ok(obraService.buscarTodasObras(paginacao));
    }

    /**
     *@param paginacao parâmetro que define a pginação
     *@return Retorna um Page, que defina os parâmetros de paginação, verifica no banco de dados e então
     * retornar um Pageobjeto do tipo Obra ao cliente.
     */
    @GetMapping("/publica")
    @Operation(summary = "Realiza a consulta de obras públicas")
    public ResponseEntity<List<ObraPublicaModel>> buscarTodasObrasPublicas(@PageableDefault(size = 10) Pageable paginacao) {
        return ResponseEntity.ok(obraService.buscarTodasObrasPublicas(paginacao));
    }

    /**
     *@param paginacao parâmetro que define a pginação
     *@return Retorna um Page, que defina os parâmetros de paginação, verifica no banco de dados e então
     * retornar um Pageobjeto do tipo Obra ao cliente.
     */
    @GetMapping("/privada")
    @Operation(summary = "Realiza a consulta de obras privadas")
    public ResponseEntity<List<ObraPrivadaModel>> buscarTodasObrasPrivadas(@PageableDefault(size = 10) Pageable paginacao) {
        return ResponseEntity.ok(obraService.buscarTodasObrasPrivadas(paginacao));
    }

    /**
     *@param codigoDoResponsavel recebe da requisição o codigo do responsável
     *@return Retorna uma Lista de Obras referente ao codigo do responsavel passado como parametro
     */
    @GetMapping("/{codigoDoResponsavel}")
    @Operation(summary = "Realiza a consulta de obras utilizando o código do responsável como chave para pesquisa")
    public ResponseEntity<List<ObraModel>> buscarObrasPorResponsavel(@PathVariable BigDecimal codigoDoResponsavel) {
        return ResponseEntity.ok(obraService.buscarObrasPorResponsavel(codigoDoResponsavel));
    }
}
