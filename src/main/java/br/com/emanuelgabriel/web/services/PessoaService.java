package br.com.emanuelgabriel.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.emanuelgabriel.web.model.Pessoa;
import br.com.emanuelgabriel.web.repository.PessoaRepository;
import br.com.emanuelgabriel.web.repository.specifications.PessoaSpecification;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;

	public Page<Pessoa> buscarTodos(String nome, String cpf, Integer idade, Pageable pageable) {
		
		if (nome == null & cpf == null & idade == null) {
			return repository.findAll(pageable);
		}
		
		return repository.findAll(Specification
				.where(PessoaSpecification.nome(nome))
				.or(PessoaSpecification.cpf(cpf))
				.or(PessoaSpecification.idade(idade)), pageable);
	}
}
