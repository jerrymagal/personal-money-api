package br.com.personal.money.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.personal.money.model.Lancamento;
import br.com.personal.money.model.filter.LancamentoFilter;
import br.com.personal.money.security.util.Roles;
import br.com.personal.money.service.LancamentoService;
import br.com.personal.money.util.ResourceUtil;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

	@Autowired
	private LancamentoService service;
	
	@GetMapping
	@PreAuthorize(Roles.ROLE_PESQUISAR_LANCAMENTO)
	public Page<Lancamento> pesquisar(LancamentoFilter filter, Pageable pageable) {
		return service.filtrar(filter, pageable);
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize(Roles.ROLE_PESQUISAR_LANCAMENTO)
	public ResponseEntity<?> buscarPorCodigo(@PathVariable Long codigo) {
		return ResourceUtil.getResponseOkOrNotFound(service.buscarPorCodigo(codigo));
	}
	
	@PostMapping
	@PreAuthorize(Roles.ROLE_CADASTRAR_LANCAMENTO)
	public ResponseEntity<Lancamento> salvar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
		service.salvar(lancamento, response);
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamento);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize(Roles.ROLE_REMOVER_LANCAMENTO)
	public void remover(@PathVariable Long codigo) {
		service.remover(codigo);
	}
}
