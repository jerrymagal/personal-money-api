package br.com.personal.money.exceptionhandler;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.personal.money.exceptionhandler.PersonalMoneyExceptionHandler.Erro;
import br.com.personal.money.service.exception.PessoaInexistenteOuInativaExcpetion;
import br.com.personal.money.util.MensagensUtil;

/**
 * Classe criada para capturar exceções de Regra de Negócio
 * @author alexandreg.rolim
 *
 */
@ControllerAdvice
public class ApplicationBusinessExceptionHandler {
	
	@ExceptionHandler({PessoaInexistenteOuInativaExcpetion.class})
	public ResponseEntity<?> handlePessoaInexistenteOuInativaExcpetion(PessoaInexistenteOuInativaExcpetion ex) {
		String mensagemUsuario = MensagensUtil.getMessage("pessoa.inexistente-ou-inativa");
		String mensagemDesenvolvedor = ex.toString();
		
		List<Erro> listaErros = Arrays.asList(new PersonalMoneyExceptionHandler.Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(listaErros);
	}

}
