package br.com.personal.money.exceptionhandler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.personal.money.util.MensagensUtil;

@ControllerAdvice
public class PersonalMoneyExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String mensagemUsuario = MensagensUtil.getMessage("mensagem.invalida");
		String mensagemDesenvolvedor = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		
		List<Erro> listaErros = criarListaErros(null);
		listaErros.add(new Erro(mensagemUsuario, mensagemDesenvolvedor));

		return handleExceptionInternal(ex, listaErros, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Erro> listaErros = criarListaErros(ex.getBindingResult());
		
		return handleExceptionInternal(ex, listaErros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity<?> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
		
		String mensagemUsuario = MensagensUtil.getMessage("recurso.nao-encontrado");
		String mensagemDesenvolvedor = ex.toString();
		
		List<Erro> listaErros = criarListaErros(null);
		listaErros.add(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return handleExceptionInternal(ex, listaErros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
		
	}
	
	private List<Erro> criarListaErros(BindingResult result) {
		
		List<Erro> listaErros = new ArrayList<>();
		
		if(result == null) {
			return listaErros;
		}
		
		String mensagemUsuario = null;
		String mensagemDesenvolvedor = null;
	
		for(FieldError fieldError : result.getFieldErrors()) {
			mensagemUsuario = MensagensUtil.getMessage(fieldError);
			mensagemDesenvolvedor = fieldError.toString();
			listaErros.add(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		}
		
		return listaErros;
	}

	public static class Erro {

		private String mensagemUsuario;
		private String mensagemDesenvolvedor;

		public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
			this.mensagemUsuario = mensagemUsuario;
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		}

		public String getMensagemUsuario() {
			return mensagemUsuario;
		}

		public String getMensagemDesenvolvedor() {
			return mensagemDesenvolvedor;
		}
	}
}
