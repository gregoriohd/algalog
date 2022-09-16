package br.com.gregorio.algalog.domain.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gregorio.algalog.domain.model.Entrega;
import br.com.gregorio.algalog.domain.repository.EntregaRespository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {

	private BuscaEntregaService buscaEntregaService;
	private EntregaRespository entregaRespository;
	
	@Transactional
	public void finalizar(Long entregaId) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		
		entrega.finalizar();
		entregaRespository.save(entrega);
	}
}
