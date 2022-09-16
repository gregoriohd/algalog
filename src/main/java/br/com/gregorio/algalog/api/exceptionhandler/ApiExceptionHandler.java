package br.com.gregorio.algalog.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.gregorio.algalog.domain.exception.NegocioServiceException;
import br.com.gregorio.algalog.domain.exception.EntidadeNaoEncontradaException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		List<Problemas.Campo> campos = new ArrayList<>();

		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			campos.add(new Problemas.Campo(nome, mensagem));
		}

		Problemas problema = new Problemas();
		problema.setStatus(status.value());
		problema.setDaraHora(OffsetDateTime.now());
		problema.setTitulo("Algo não está bem! Verifique o que foi digitado e corrija para continuar.");
		problema.setCampos(campos);

		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}

	@ExceptionHandler(NegocioServiceException.class)
	public ResponseEntity<Object> handleService(NegocioServiceException csx, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Problemas problema = new Problemas();
		problema.setStatus(status.value());
		problema.setDaraHora(OffsetDateTime.now());
		problema.setTitulo(csx.getMessage());
		return handleExceptionInternal(csx, problema, new HttpHeaders(), status, request);
	}
	
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException exception, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		Problemas problema = new Problemas();
		problema.setStatus(status.value());
		problema.setDaraHora(OffsetDateTime.now());
		problema.setTitulo(exception.getMessage());
		return handleExceptionInternal(exception, problema, new HttpHeaders(), status, request);
	}
}
