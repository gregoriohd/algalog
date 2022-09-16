package br.com.gregorio.algalog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.gregorio.algalog.api.model.OcorrenciaModel;
import br.com.gregorio.algalog.api.model.input.OcorrenciaInput;
import br.com.gregorio.algalog.domain.model.Ocorrencia;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OcorrenciaAssembler {

	private ModelMapper mapper;
	
	public OcorrenciaModel toModel(Ocorrencia ocorrencia) {
		return mapper.map(ocorrencia, OcorrenciaModel.class);	
	}
	
	public List<OcorrenciaModel> toCollectionList(List<Ocorrencia> ocorrencias){
		return ocorrencias.stream().map(this::toModel).collect(Collectors.toList());
	}
	
	public Ocorrencia toEntity(OcorrenciaInput ocorrenciaInput) {
		return mapper.map(ocorrenciaInput, Ocorrencia.class);
	}
}
