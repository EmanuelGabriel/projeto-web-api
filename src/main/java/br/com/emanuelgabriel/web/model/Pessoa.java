package br.com.emanuelgabriel.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.emanuelgabriel.web.model.enums.Genero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_PESSOA")
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false, updatable = false)
	private Long id;

	@Column(name = "NOME")
	private String nome;

	@Column(name = "CPF")
	private String cpf;

	@Column(name = "IDADE")
	private Integer idade;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "GENERO")
	private Genero genero;

}
