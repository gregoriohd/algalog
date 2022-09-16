package br.com.gregorio.algalog.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteIdInput {

	@NotBlank
	private Long id;
}