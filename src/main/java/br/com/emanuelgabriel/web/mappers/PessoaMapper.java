package br.com.emanuelgabriel.web.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.emanuelgabriel.web.dtos.response.PessoaResponseDTO;
import br.com.emanuelgabriel.web.model.Pessoa;

/**
 * 
 * @author emanuel.sousa
 *
 */

@Component
public class PessoaMapper implements EntityMapper<PessoaResponseDTO, Pessoa> {

	@Autowired
	private ModelMapper mapper;

	@Override
	public Pessoa toEntity(PessoaResponseDTO dto) {
		return mapper.map(dto, Pessoa.class);
	}

	@Override
	public PessoaResponseDTO toDto(Pessoa entity) {
		return mapper.map(entity, PessoaResponseDTO.class);
	}

	@Override
	public List<Pessoa> toEntity(List<PessoaResponseDTO> dtoList) {
		return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
	}

	@Override
	public List<PessoaResponseDTO> toDto(List<Pessoa> entityList) {
		return entityList.stream().map(this::toDto).collect(Collectors.toList());
	}

	@Override
	public Page<PessoaResponseDTO> mapEntityPageToDTO(Pageable pageable, Page<Pessoa> pageEntity) {
		var listDTO = toDto(pageEntity.getContent());
		return new PageImpl<>(listDTO, pageable, pageEntity.getTotalElements());

	}

}
