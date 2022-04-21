package br.com.emanuelgabriel.web.controller;

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

import br.com.emanuelgabriel.web.model.Pessoa;
import br.com.emanuelgabriel.web.services.PessoaService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author emanuel.sousa
 */

@Slf4j
@RestController
@RequestMapping(value = "/pessoas", produces = MediaType.APPLICATION_JSON_VALUE)
public class PessoaController {
	
	@Autowired
	private PessoaService service;

	@GetMapping
	public ResponseEntity<Page<Pessoa>> buscarTodasPessoas(
			@RequestParam(name = "nome", required = false) String nome,
			@RequestParam(name = "cpf", required = false) String cpf, 
			@RequestParam(name = "idade", required = false) Integer idade,
			@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable pageable) {
		log.info("GET /pessoas - nome: {}; cpf: {}; idade: {}", nome, cpf, idade, pageable);
		var pageResultado = service.buscarTodos(nome, cpf, idade, pageable);
		return pageResultado != null ? ResponseEntity.ok().body(pageResultado) : ResponseEntity.ok().build();
	}


}
