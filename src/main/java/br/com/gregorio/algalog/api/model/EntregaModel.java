package br.com.gregorio.algalog.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import br.com.gregorio.algalog.domain.model.StatusEntrega;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaModel {
	
	
	private Long id;
	
	private ClienteResumoModel cliente;
	
	private String nomeCLiente;
	
	private DestinatarioModelo destinatario;
	
	private BigDecimal taxa;
	
	private StatusEntrega status;
	
	private OffsetDateTime dataPedido;
	
	private OffsetDateTime dataFinalizacao;

}
