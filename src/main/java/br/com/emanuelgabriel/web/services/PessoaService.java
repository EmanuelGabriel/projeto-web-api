package br.com.emanuelgabriel.web.services;

import br.com.emanuelgabriel.web.dtos.response.PessoaResponseDTO;
import br.com.emanuelgabriel.web.mappers.PessoaMapper;
import br.com.emanuelgabriel.web.model.Pessoa;
import br.com.emanuelgabriel.web.repository.PessoaRepository;
import br.com.emanuelgabriel.web.repository.filter.PessoaFiltro;
import br.com.emanuelgabriel.web.repository.specifications.PessoaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PessoaService {

    private static final Logger LOG = Logger.getLogger(PessoaService.class.getName());

    @Autowired
    private PessoaRepository repository;

    @Autowired
    private PessoaMapper pessoaMapper;

    public Page<Pessoa> buscarTodos(String nome, String cpf, Integer idade, Pageable pageable) {
        LOG.log(Level.INFO, "BuscarTodos por: nome: {0}; cpf: {1}; idade: {2}; pageNumber: {3}; pageSize: {4}", new Object[]{nome, cpf, idade, pageable.getPageNumber(), pageable.getPageSize()});

        if (nome == null & cpf == null & idade == null) {
            return repository.findAll(pageable);
        }

        return repository.findAll(Specification
                .where(PessoaSpecification.nome(nome))
                .or(PessoaSpecification.cpf(cpf))
                .or(PessoaSpecification.idade(idade)), pageable);
    }

    /**
     * @param nome
     * @param cpf
     * @return List<Pessoa>
     */
    public List<Pessoa> buscarPor(String nome, String cpf, Integer idadeMaior, Integer idadeMenor) {
        return repository.findAll((Specification<Pessoa>) (root, query, builder) -> {
            LOG.log(Level.INFO, "Buscar por nome: {0}; cpf: {1}; idadeMaior: {2}; idadeMenor: {3}", new Object[]{nome, cpf, idadeMaior, idadeMenor});

            List<Predicate> predicates = new ArrayList<>();

            if (!ObjectUtils.isEmpty(nome)) {
                predicates.add(builder.like(builder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));
            }

            if (!ObjectUtils.isEmpty(cpf)) {
                predicates.add(builder.like(builder.lower(root.get("cpf")), "%" + cpf.toLowerCase() + "%"));
            }

            if (!ObjectUtils.isEmpty(idadeMaior) && !ObjectUtils.isEmpty(idadeMenor)) {
                predicates.add(builder.between(root.get("idade"), idadeMaior, idadeMenor));
            }

            // Ordenação
            query.orderBy(builder.asc(root.get("nome")));

            return builder.and(predicates.toArray(new Predicate[0]));
        });
    }

    /**
     * Filtrar
     *
     * @param filtro
     * @return List<pessoa>
     */
    public List<Pessoa> filtrarPor(PessoaFiltro filtro) {
        LOG.log(Level.INFO, "Buscar por filtro: {0}", filtro);

        return repository.findAll((Specification<Pessoa>) (root, query, builder) -> {

            List<Predicate> predicates = new ArrayList<>();

            // verifica se os campos existem
            if (!ObjectUtils.isEmpty(filtro.getNome())) {
                predicates.add(builder.like(builder.lower(root.get("nome")), "%" + filtro.getNome().toLowerCase() + "%"));
            }

            if (!ObjectUtils.isEmpty(filtro.getCpf())) {
                predicates.add(builder.like(builder.lower(root.get("cpf")), "%" + filtro.getCpf().toLowerCase() + "%"));
            }

            if (!ObjectUtils.isEmpty(filtro.getIdade())) {
                predicates.add(builder.equal(root.get("idade"), filtro.getIdade()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        });
    }


    /**
     * @param filtro
     * @return Page<PessoaResponseDTO
     */
    public Page<PessoaResponseDTO> filtrarPaginadoPor(PessoaFiltro filtro, Pageable pageable) {
        LOG.log(Level.INFO, "Busca paginada por filtro: {0};{1}", new Object[]{ filtro, pageable});

        Page<Pessoa> pagePessoa = repository.findAll((Specification<Pessoa>) (root, query, builder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (!ObjectUtils.isEmpty(filtro.getNome())) {
                predicates.add(builder.like(builder.lower(root.get("nome")), "%" + filtro.getNome().toLowerCase() + "%"));
            }

            if (!ObjectUtils.isEmpty(filtro.getCpf())) {
                predicates.add(builder.like(builder.lower(root.get("cpf")), "%" + filtro.getCpf().toLowerCase() + "%"));
            }

            if (!ObjectUtils.isEmpty(filtro.getIdade())) {
                predicates.add(builder.equal(root.get("idade"), filtro.getIdade()));
            }

            /**
            if (!ObjectUtils.isEmpty(filtro.getIdadeInicio())) {
                var idadeInicio = builder.greaterThanOrEqualTo(root.get("idade"), filtro.getIdadeInicio());
                var idadeFim = builder.lessThanOrEqualTo(root.get("idade"), filtro.getIdadeFim());
                predicates.add(idadeInicio);
                predicates.add(idadeFim);
            }*/


            if (!ObjectUtils.isEmpty(filtro.getIdadeInicio())) {
                predicates.add(builder.between(root.get("idade"), filtro.getIdadeInicio(), filtro.getIdadeFim()));
            }

            // Ordenação feita por nome
            query.orderBy(builder.asc(root.get("nome")));

            return builder.and(predicates.toArray(new Predicate[0]));
        }, pageable);

        pagePessoa.getTotalElements();
        pagePessoa.getTotalPages();

        return pessoaMapper.mapEntityPageToDTO(pageable, pagePessoa);

    }
}
