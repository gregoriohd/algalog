package br.com.gregorio.algalog.domain.exception;

public class NegocioServiceException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NegocioServiceException(String mensagem) {
		super(mensagem);
	}
}
