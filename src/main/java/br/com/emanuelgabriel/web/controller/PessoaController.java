package br.com.emanuelgabriel.web.controller;

import br.com.emanuelgabriel.web.dtos.response.PessoaResponseDTO;
import br.com.emanuelgabriel.web.model.Pessoa;
import br.com.emanuelgabriel.web.repository.filter.PessoaFiltro;
import br.com.emanuelgabriel.web.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author emanuel.sousa
 */

@RestController
@RequestMapping(value = "/pessoas", produces = MediaType.APPLICATION_JSON_VALUE)
public class PessoaController {

    private static final Logger LOG = Logger.getLogger(PessoaController.class.getName());
    @Autowired
    private PessoaService service;

    @GetMapping
    public ResponseEntity<Page<Pessoa>> buscarTodasPessoas(
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "cpf", required = false) String cpf,
            @RequestParam(name = "idade", required = false) Integer idade,
            @PageableDefault(page = 0, size = 10, sort = "nome",  direction = Sort.Direction.ASC) Pageable pageable) {
        LOG.log(Level.INFO, "GET /pessoas - nome: {0}; cpf: {1}; idade: {2}; pageNumber: {3}; pageSize: {4}", new Object[]{nome, cpf, idade, pageable.getPageNumber(), pageable.getPageSize()});
        var pageResultado = service.buscarTodos(nome, cpf, idade, pageable);
        return pageResultado != null ? ResponseEntity.ok().body(pageResultado) : ResponseEntity.ok().build();
    }

    @GetMapping(value = "/nome", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pessoa>> buscarPorNome(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "cpf", required = false) String cpf,
            @RequestParam(value = "idadeMaior", required = false) Integer idadeMaior,
            @RequestParam(value = "idadeMenor", required = false) Integer idadeMenor) {
        LOG.log(Level.INFO, "GET /pessoas/nome - nome: {0}; cpf: {1}", new Object[] { nome, cpf, idadeMaior, idadeMenor });
        var resultado = service.buscarPor(nome, cpf, idadeMaior, idadeMenor);
        return resultado != null ? ResponseEntity.ok().body(resultado) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/filtro", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pessoa>> fitrarPor(PessoaFiltro filtro) {
        LOG.log(Level.INFO, "GET /pessoas/filtro - filtro: {0}", filtro);
        var resultado = service.filtrarPor(filtro);
        return resultado != null ? ResponseEntity.ok().body(resultado) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/filtro/paginado", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<PessoaResponseDTO>> fitrarPaginadoPor(PessoaFiltro filtro,
        @PageableDefault(page = 0, size = 10, sort = "nome",  direction = Sort.Direction.ASC) Pageable pageable) {
        LOG.log(Level.INFO, "GET /pessoas/filtro/paginado - filtro: {0};{1}", new Object[]{ filtro, pageable });
        var resultado = service.filtrarPaginadoPor(filtro, pageable);
        return resultado != null ? ResponseEntity.ok().body(resultado) : ResponseEntity.notFound().build();
    }

}
