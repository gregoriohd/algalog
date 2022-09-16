package br.com.gregorio.algalog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.gregorio.algalog.api.model.EntregaModel;
import br.com.gregorio.algalog.domain.model.Entrega;
import br.com.gregorio.algalog.domain.model.input.EntregaInput;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EntregaAssembler {

	private ModelMapper mapper;

	public EntregaModel toModel(Entrega entrega) {
		return mapper.map(entrega, EntregaModel.class);
	}

	public List<EntregaModel> toCollectionModel(List<Entrega> entregas) {
		return entregas.stream().map(this::toModel).collect(Collectors.toList());
	}
	
	public Entrega toEntity(EntregaInput entregaInput) {
		return mapper.map(entregaInput, Entrega.class);
	}
}
