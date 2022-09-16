package br.com.gregorio.algalog.domain.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gregorio.algalog.api.assembler.EntregaAssembler;
import br.com.gregorio.algalog.api.model.EntregaModel;
import br.com.gregorio.algalog.domain.model.Cliente;
import br.com.gregorio.algalog.domain.model.Entrega;
import br.com.gregorio.algalog.domain.model.StatusEntrega;
import br.com.gregorio.algalog.domain.repository.EntregaRespository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

	private CatalogoClienteService catalogoClienteService;
	private EntregaRespository entregaRespository;
	private EntregaAssembler entregaAssembler;

	@Transactional
	public Entrega solicitar(Entrega entrega) {
		Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());
		entrega.setCliente(cliente);
		entrega.setStatusEsntrega(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());

		return entregaRespository.save(entrega);
	}

	@Transactional
	public List<EntregaModel> listar() {
		return entregaAssembler.toCollectionModel(entregaRespository.findAll());
	}

	@Transactional
	public ResponseEntity<EntregaModel> buscar(Long entregaId) {
		return entregaRespository.findById(entregaId)
				.map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega)))
				.orElse(ResponseEntity.notFound().build());
	}

}
