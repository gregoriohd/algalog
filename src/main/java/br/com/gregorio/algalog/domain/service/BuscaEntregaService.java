package br.com.gregorio.algalog.domain.service;

import org.springframework.stereotype.Service;

import br.com.gregorio.algalog.domain.exception.EntidadeNaoEncontradaException;
import br.com.gregorio.algalog.domain.model.Entrega;
import br.com.gregorio.algalog.domain.repository.EntregaRespository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {

	private EntregaRespository entregaRespository;

	public Entrega buscar(Long entregaId) {
		return entregaRespository.findById(entregaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Entrga mão encontrada"));
	}

}
