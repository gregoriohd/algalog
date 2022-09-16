package br.com.gregorio.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import br.com.gregorio.algalog.api.assembler.EntregaAssembler;
import br.com.gregorio.algalog.api.model.EntregaModel;
import br.com.gregorio.algalog.domain.model.Entrega;
import br.com.gregorio.algalog.domain.model.input.EntregaInput;
import br.com.gregorio.algalog.domain.service.FinalizacaoEntregaService;
import br.com.gregorio.algalog.domain.service.SolicitacaoEntregaService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private SolicitacaoEntregaService solicitacaoEntregaService;
	private EntregaAssembler entregaAssembler;
	private FinalizacaoEntregaService finalizacaoEntregaService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaModel solicitar(@Valid @RequestBody EntregaInput entregaInput) {
		Entrega converterEntrega = entregaAssembler.toEntity(entregaInput);
		Entrega solicitacao = solicitacaoEntregaService.solicitar(converterEntrega);
		return entregaAssembler.toModel(solicitacao);
	}

	@GetMapping
	public List<EntregaModel> listar() {
		return solicitacaoEntregaService.listar();
	}

	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId) {
		return solicitacaoEntregaService.buscar(entregaId);

	}
	
	@PutMapping("/{entregaId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long entregaId) {
		finalizacaoEntregaService.finalizar(entregaId);
	}

}
