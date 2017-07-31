package br.com.personal.money.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.personal.money.util.MensagensUtil;

@ControllerAdvice
public class PersonalMoneyExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String mensagemUsuario = MensagensUtil.getMessage("mensagem.invalida");
		String mensagemDesenvolvedor = ex.getCause().toString();

		return handleExceptionInternal(ex, new Erro(mensagemUsuario, mensagemDesenvolvedor), headers, status, request);
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
