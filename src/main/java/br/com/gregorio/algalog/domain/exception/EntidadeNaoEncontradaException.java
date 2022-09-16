package br.com.gregorio.algalog.domain.exception;

public class EntidadeNaoEncontradaException extends NegocioServiceException {

	private static final long serialVersionUID = -1497200887647507438L;

	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
		
	}

}
