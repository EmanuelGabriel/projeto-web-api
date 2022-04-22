package br.com.emanuelgabriel.web.repository.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaFiltro {

    private String nome;
    private String cpf;
    private Integer idade;
}
