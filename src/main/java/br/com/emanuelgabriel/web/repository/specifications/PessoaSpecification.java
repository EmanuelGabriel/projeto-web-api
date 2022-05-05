package br.com.emanuelgabriel.web.repository.specifications;

import org.springframework.data.jpa.domain.Specification;

import br.com.emanuelgabriel.web.model.Pessoa;

public class PessoaSpecification {

	/**
	 * 
	 * @param nome
	 * @return
	 */
	public static Specification<Pessoa> nome(String nome) {
		return (root, criteriaQuery, builder) ->
			builder.like(builder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
	}
	
	
	/**
	 * 
	 * @param cpf
	 * @return
	 */
	public static Specification<Pessoa> cpf(String cpf) {
		return (root, criteriaQuery, criteriaBuilder) -> 
			criteriaBuilder.equal(root.get("cpf"), cpf);
	}
	
	/**
	 * 
	 * @param idade
	 * @return
	 */
	public static Specification<Pessoa> idade(Integer idade) {
		return (root, criteriaQuery, criteriaBuilder) -> 
			criteriaBuilder.equal(root.get("idade"), idade);
	}




}
